(function(window, document, Granite, $) {

    $(".upload-button").hide();
    $(".ocm-wait").hide();
    $(".ocm-dl-dialog").show();

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
                console.log('Upload Button Clicked');
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
                    variant: "info"
                  });

                    let url = '/bin/uploadassets';
                    fetch(url, { method: 'POST' })
                        .then(Result => Result.json())
                         .then(string => {
                           document.body.appendChild(ocmAssetUploadDlg);
                           ocmAssetUploadDlg.show();
                           $(".ocm-wait").hide();
                          })
                     .catch(errorMsg => { console.log(errorMsg); });
            }
        });
})(window, document, Granite, Granite.$);