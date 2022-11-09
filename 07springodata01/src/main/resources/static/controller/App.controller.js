sap.ui.define([
   "sap/ui/core/mvc/Controller"
], function (Controller) {
   "use strict";
   return Controller.extend("capm.controller.App", {
   		onInit: function(){
   			var oDataModel = new sap.ui.model.odata.v2.ODataModel("/sample.svc");
			this.getView().setModel(oDataModel);
   		}
   });
});