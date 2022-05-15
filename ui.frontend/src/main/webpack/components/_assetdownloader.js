(function(window, document, Granite, $) {

    $(".upload-button").hide();
    $(".ocm-wait").hide();
    $(".ocm-dl-dialog").hide();
    $(".ocm-success-alert").hide();

    successAlert = (msg) => {
      $(".ocm-success-alert").find("coral-alert-content").html(msg);
      $(".ocm-success-alert").fadeIn(1000);
      $(".ocm-success-alert").fadeOut(1000)
     };

    errorAlert = (msg) => {
      $(".ocm-error-alert").find("coral-alert-content").html(msg);
      $(".ocm-error-alert").fadeIn(1000);
      $(".ocm-error-alert").fadeOut(1000)
     };

    $(window).adaptTo("foundation-registry").register("foundation.collection.action.action", {
        name: "cq.wcm.ocm.select",
        handler: function(name, el, config, collection, selections) {

            if (selections.length != 1) {
                return;
            }

             selections.map((item) => {
                item.selected=!item.selected;
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
            handler: function(name, el, config, collection, selections) {
                console.log('Upload Bu  tton Clicked');
                $(".ocm-wait").show();
                let selectedItems = $(".coral3-Masonry-item.is-selected");
                console.log(selectedItems.length);
                $(".ocm-dl-dialog").show();
                let ocmAssetUploadDlg = new Coral.Dialog().set({
                    id: "ocm-dl-success",
                    header: {
                      innerHTML: "AEM Asset Upload"
                    },
                    content: {
                      innerHTML: "Asset upload request sent.."
                    },
                    footer: {
                      innerHTML: "<button is=\"coral-button\" variant=\"primary\" coral-close=\"\" class=\"coral3-Button coral3-Button--primary\" size=\"M\"><coral-button-label>Ok</coral-button-label></button>"
                    },
                    variant: "default"
                  });

                    let url = '/bin/uploadassets';
                    fetch(url, { method: 'POST' })
                        .then(Result => Result.json())
                         .then(string => {
                           document.body.appendChild(ocmAssetUploadDlg);
                           ocmAssetUploadDlg.show();
                           $(".ocm-wait").hide();
                           let selectedItems = $(".coral3-Masonry-item.is-selected");
                           selectedItems.map(item => item.selected = false);
                          })
                     .catch(errorMsg => { errorAlert(errorMsg); });
            }
        });

         $(window).adaptTo("foundation-registry").register("foundation.collection.action.action", {
                    name: "cq.wcm.ocm.back",
                    handler: function(name, el, config, collection, selections) {
                        window.history.go(-1);
                    }
                });

          $(window).adaptTo("foundation-registry").register("foundation.collection.action.action", {
                        name: "cq.wcm.ocm.link",
                        handler: function(name, el, config, collection, selections) {
                               if (selections.length != 1) {
                                            return;
                                        }

                               selections.map((item) => {
                                    let link = $(item).attr("data-href");
                                    window.navigator.clipboard.writeText(link);
                                    successAlert("Asset link copied.")
                                    return true;
                                });
                        }
                    });

           $(window).adaptTo("foundation-registry").register("foundation.collection.action.action", {
                name: "cq.wcm.ocm.embed",
                handler: function(name, el, config, collection, selections) {
                       if (selections.length != 1) {
                                    return;
                                }

                       selections.map((item) => {
                            let link = $(item).attr("data-href");
                            let img = `<img src="${link}" alt="" />`;
                            window.navigator.clipboard.writeText(img);
                            successAlert("Asset embed copied.")
                            return true;
                        });
                }
            });


            let container = $(".foundation-layout-panel-content");
            let scrollToEndCount = 0;
            container.scroll(() => {
                if(container.scrollTop() + container.innerHeight() >= container[0].scrollHeight) {
                       $(".ocm-wait").show();
                       scrollToEndCount++;
                       let url = `/content/ocmassets/us/en/asset-downloader-items.html?wcmmode=disabled&offset=${40*scrollToEndCount}`;
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
                            .catch(errorMsg => { errorAlert(errorMsg);$(".ocm-results-wait").hide(); });

                }
            });

})(window, document, Granite, Granite.$);