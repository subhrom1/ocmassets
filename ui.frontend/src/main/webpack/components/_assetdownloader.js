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
            }
        });
})(window, document, Granite, Granite.$);