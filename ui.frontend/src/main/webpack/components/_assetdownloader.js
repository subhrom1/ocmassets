(function (window, document, Granite, $) {

    const UPLOAD_ASSETS_URL = "/bin/uploadassets";

    const FETCH_MORE_CARDS_URL = "/content/ocmassets/us/en/asset-downloader-items";

    $('.xDialog').css({'display':'block'});

    const successAlert = (msg) => {
        $(".ocm-success-alert").find("coral-alert-content").html(msg);
        $(".ocm-success-alert").fadeIn(1000);
        $(".ocm-success-alert").fadeOut(2000)
    };

    const errorAlert = (msg) => {
        $(".ocm-error-alert").find("coral-alert-content").html(msg);
        $(".ocm-error-alert").fadeIn(2000);
        $(".ocm-error-alert").fadeOut(3000)
    };

    $(window).adaptTo("foundation-registry").register("foundation.collection.action.action", {
        name: "cq.wcm.ocm.select",
        handler: function (name, el, config, collection, selections) {

            if (selections.length != 1) {
                return;
            }

            selections.map((item) => {
                item.selected = !item.selected;
                let selectedItems = $(".coral3-Masonry-item.is-selected");
                if (item.selected && selectedItems.length == 0) {
                    $(".upload-button").show();
                } else if (!item.selected && selectedItems.length == 1) {
                    $(".upload-button").hide();
                }
                return true;
            });
        }
    });

    $(window).adaptTo("foundation-registry").register("foundation.collection.action.action", {
        name: "cq.wcm.ocm.upload",
        handler: function (name, el, config, collection, selections) {
            $(".ocm-wait").show();
            let selectedItems = $(".coral3-Masonry-item.is-selected");

            let itemArr = [];
            for (let item of selectedItems) {
                let link = $(item).attr("data-href");
                let title = $(item).attr("data-foundation-collection-item-id");
                if (link && title) {
                    itemArr.push({"assetTitle":title, "assetLink":link});
                    item.selected = false;
                }
            }

            let uploadPath = $('.js-coral-pathbrowser-input').val();

            if (!uploadPath) {
                errorAlert("Please specify asset upload path");
                $(".ocm-wait").hide();
                $(".upload-button").fadeOut(1000);
                return;
            }

            fetch(UPLOAD_ASSETS_URL, {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json; charset=UTF-8'
                },
                body: JSON.stringify({"damUploadPath": uploadPath, "selectedAssets":itemArr})
            })
                .then(Result => Result.json())
                .then(message => {
                    successAlert("Assets enqueued for upload to AEM..");
                    $(".ocm-wait").hide();
                    $(".upload-button").fadeOut(1000);
                })
                .catch(errorMsg => {
                    errorAlert("Error uploading assets..");
                    $(".ocm-wait").hide();
                    $(".upload-button").fadeOut(1000);
                });
        }
    });

    $(window).adaptTo("foundation-registry").register("foundation.collection.action.action", {
        name: "cq.wcm.ocm.back",
        handler: function (name, el, config, collection, selections) {
            window.history.go(-1);
        }
    });

    $(window).adaptTo("foundation-registry").register("foundation.collection.action.action", {
        name: "cq.wcm.ocm.link",
        handler: function (name, el, config, collection, selections) {
            if (selections.length != 1) {
                return;
            }

            selections.forEach((item) => {
                let link = $(item).attr("data-href");
                window.navigator.clipboard.writeText(link);
                successAlert("Asset link copied.")
            });
        }
    });

    $(window).adaptTo("foundation-registry").register("foundation.collection.action.action", {
        name: "cq.wcm.ocm.embed",
        handler: function (name, el, config, collection, selections) {
            if (selections.length != 1) {
                return;
            }

            selections.map((item) => {
                let link = $(item).attr("data-href");
                let img = `<img src="${link}" alt="ocm-image" height="400" width="700"/>`;
                window.navigator.clipboard.writeText(img);
                successAlert("Asset embed copied.");
                return true;
            });
        }
    });


    let container = $(".foundation-layout-panel-content");
    let scrollToEndCount = 0;
    container.scroll(() => {
        if (container.scrollTop() + container.innerHeight() >= container[0].scrollHeight) {
            $(".ocm-wait").show();
            scrollToEndCount++;
            let url = `${FETCH_MORE_CARDS_URL}.${40 * scrollToEndCount}.20.html?wcmmode=disabled`;
            fetch(url, { method: 'GET' })
                .then(Result => Result.text())
                .then(html => {
                    let docx = $.parseHTML(html);
                    let results = $(docx).find(".coral3-Masonry-item");
                    if (results && results.length > 0) {
                        $(".coral3-Masonry").append(results);

                    }
                    $(".ocm-wait").hide();
                })
                .catch(errorMsg => {
                    errorAlert("Error fetching data..");
                    $(".ocm-results-wait").hide();
                });

        }
    });
})(window, document, Granite, Granite.$);