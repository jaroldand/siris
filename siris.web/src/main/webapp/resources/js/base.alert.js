window.jnoty = window.jnoty
		|| (function() {

			var typeMessage = {
				error : "error",
				warning : "warning",
				info : "information",
				success : "success",
				alert : "alert"
			};

			function escapeSpecialChars(jsonString) {
				if (typeof jsonString === "undefined")
					return jsonString;
				return jsonString.replace(/\\"/g, "");
			}
			;

			var getMessageValid = function(message) {
				var jsonMessage;
				if (typeof message === "undefined")
					return "";
				try {
					message = escapeSpecialChars(message);
					jsonMessage = JSON.parse(message);
				} catch (e) {
					jsonMessage = message;
				}
				if (jsonMessage.error)
					return jsonMessage.error;
				else if (jsonMessage.errorInfo)
					return jsonMessage.errorInfo.message;
				else if (jsonMessage.Message)
					return jsonMessage.Message;
				return message;

			};

			var showMessage = function(type, message, timer, modal) {
				message = getMessageValid(message);

				var n = noty({
					text : message,
					type : type,
					dismissQueue : true,
					layout : "topCenter",
					theme : "bootstrapTheme",
					closeWith : [ "button", "click" ],
					maxVisible : 5,
					template : "<div class='noty_message'><div class='noty_close'/><p class='noty_text'></p></div>",
					timeout : timer ? timer : 5000,
					modal : modal ? modal : false,
					progressBar : true
				});
			};

			var confirm = function(message, fnconfirm) {
				var n = noty({
					text : message,
					type : typeMessage.info,
					dismissQueue : false,
					layout : "center",
					theme : "bootstrapTheme", // defaultTheme
					modal : true,
					maxVisible : 1,
					template : "<div class=\"noty_message panel panel-default\" style=\"margin-bottom:0 !important\" >  <div class=\"panel-body\"><span class=\"noty_text\" style=\"word-wrap: break-word;\"></span></div><div class=\"panel-footer panel-footer-delete\"></div></div>",
					buttons : [ {
						addClass : "btn btn-info btn-sm btn-si",
						text : "Aceptar",
						onClick : function($noty) {
							if (typeof (fnconfirm) === "function")
								fnconfirm();
							$noty.close();
						}
					}, {
						addClass : "btn btn-default btn-sm btn-no",
						text : "Cancelar",
						onClick : function($noty) {
							$.noty.clearQueue(); // Clears the notification
							// queue
							$noty.close();
						}
					} ]
				});

				$("[class*='btn-no']").focus();

			};
			var accept = function(message, fnconfirm) {
				var n = noty({
					text : message,
					type : typeMessage.info,
					dismissQueue : false,
					layout : "center",
					theme : "bootstrapTheme", // defaultTheme
					modal : true,
					maxVisible : 1,
					template : "<div class=\"noty_message panel panel-default\" style=\"margin-bottom:0 !important\" >  <div class=\"panel-body\"><span class=\"noty_text\" style=\"word-wrap: break-word;\"></span></div><div class=\"panel-footer panel-footer-delete\"></div></div>",
					buttons : [ {
						addClass : "btn btn-info btn-sm btn-si",
						text : "Aceptar",
						onClick : function($noty) {
							if (typeof (fnconfirm) === "function")
								fnconfirm();
							$noty.close();
						}
					}]
				});

			};
			var error = function(message) {
				showMessage(typeMessage.error, message, 10000);
			};
			var warning = function(message) {
				showMessage(typeMessage.warning, message, 10000);
			};
			var information = function(message) {
				showMessage(typeMessage.info, message, 15000, true);
			};
			var success = function(message) {
				showMessage(typeMessage.success, message, 6000);
			};
			var alert = function(message) {
				showMessage(typeMessage.alert, message, 6000);
			};

			// var errorPage = function (message) {
			//
			// //message =
			// message.replace("Sys.WebForms.PageRequestManagerServerErrorException:",
			// "").trim();
			// var err = null;
			// try {
			// err = JSON.parse(message);
			// } catch (e) {
			//
			// }
			//
			// if (err) {
			//
			// var msg = err.error + "<br\>" + "Código Log: " + err.codlog;
			// if (err.nivel === "None" || err.nivel === "Warning")
			// warning(msg);
			// else if (err.nivel === "Information")
			// info(msg);
			// else if (err.nivel === "Error")
			// error(msg);
			//
			// } else error(message);
			//
			// };

			// var catchErrorPage = function (sender, args) {
			// if (args.get_error() != undefined) {
			// errorPage(args.get_error().message);
			// }
			// };

			return {
				showMessage : showMessage,
				error : error,
				warning : warning,
				information : information,
				success : success,
				alert : alert,
				typeMessage : typeMessage,
				confirm : confirm,
				accept : accept
			// catchErrorPage: catchErrorPage,
			// getMessageValid: getMessageValid
			};

		}());
