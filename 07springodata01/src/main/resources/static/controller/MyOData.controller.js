sap.ui.define([
   "sap/ui/core/mvc/Controller",
   "jquery.sap.global",
   "capm/util/service",
   "sap/m/MessageBox",
   "sap/ui/core/Fragment"
], function (Controller, jQuery, service, MessageBox, Fragment) {
   "use strict";
   return Controller.extend("capm.controller.Main", {
   
   		onInit: function(){
			var oModel = new sap.ui.model.json.JSONModel();
			oModel.setData({"vendor": {
			    "CompanyName": "IBM",
			    "FirstName": "Veronica",
			    "LastName": "Victoria",
			    "Website": "ibm.com",
			    "Email": "veronica@sap.com",
			    "Status": "A",
			    "RegDate": "GSTIN465465",
			    "Addresses":[{
			        "AddressType": "H",
			        "Street": "MG Road",
			        "City": "Gurgaon",
			        "Country": "India",
			        "Region": "APJ"
			    }]
			}});
			//Set the model object to the Entire App level
			this.getView().setModel(oModel, "local");
   		},
   		
   		onAdd: function(){
   			if(!this.oDialog){
   				Fragment.load(
   					{
   					id: this.getView().getId(),
   					name: "capm.fragments.Add",
   					controller:this
   					}
   				).then(
   					function (oDialog){
   						this.oDialog = oDialog;
   						this.oDialog.setModel(this.getView().getModel("local"));
   						this.getView().addDependent(this.oDialog);
   						this.oDialog.open();
   					}.bind(this));
   			} else {
   				this.oDialog.open();
   			}
   		},
   		
   		onClose:function(){
   			this.oDialog.close();
   		},
   		
   		onSave: function(){
   			var oPayload = this.getView().getModel("local").getProperty("/vendor");
			this.getView().getModel().create("/VendorSet", oPayload,{
				success: function(){
					//MessageToast.show("Vendor has been created Successfully");
					this.oDialog.close();
				}.bind(this),
				error: function(oError){
					console.log(oError);
				}
			});
   		}
   });
});