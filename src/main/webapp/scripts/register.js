(function(){
	$(document).ready(function(){
		var container = $(".registration-container");
		var actionButton = container.find(".action-button");
		var birthDate = container.find("input[name=birthdate]");
		var integerInputs = container.find("input[name=house], input[name=appartment]");
		var inputs = container.find("input[type=text], input[type=email]");
		var passwords = container.find("input[type=password]");
		
		inputs.on("focus", function(){
			hideError(this);
		});
		passwords.on("focus", function(){
			for(var i=0; i< passwords.length; i++){
				hideError(passwords[i]);
			}
		});
		
		birthDate.on("blur", function(){
			var dateString = birthDate.val();
			if(dateString != null && dateString != ""){
				var dateArray = dateString.split("-");
				if(dateArray.length != 3){
					showError(birthDate);
					return false;
				}
				var y = dateArray[0];
				var m = dateArray[1];
				var d = dateArray[2];
				var date = new Date(dateString);
				if(date.getFullYear() != y || (date.getMonth() + 1) != m || date.getDate() != d){
					showError(birthDate);
					return false;
				}
			} 
		});
		
		passwords.on("blur", function(){
			var pass = passwords[0];
			var repeat = passwords[1];
			if(repeat.value != null && repeat.value.length && pass.value !== repeat.value){
				showError(repeat);
			}
		});
		
		integerInputs.on("keypress", setOnlyDigits);
		
		actionButton.click(function(){
			var isHasError = $(".fe-error:visible").length > 0;
			if(isHasError){
				return false;
			}
		});
	});
	
	function showError(element){
		$(element).nextAll(".fe-error").first().show();
	}
	
	function hideError(element){
		$(element).nextAll(".fe-error").first().hide();
	}
	
	function setOnlyDigits(e) {
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			e.preventDefault();
		}
	}
	
})();