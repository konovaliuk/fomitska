(function(){
	$(document).ready(function(){
		var container = $(".registration-container");
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
			if(pass.value !== repeat.value){
				showError(this);
			}
		});
		
		integerInputs.on("blur", function(){
			if(this.value != null && this.value != "" 
			&& Number.isNaN(Number(this.value))){
				showError(this);
			}
		});
	});
	
	function showError(element){
		$(element).next().removeClass("unvisible");
	}
	
	function hideError(element){
		$(element).next().addClass("unvisible");
	}
})();