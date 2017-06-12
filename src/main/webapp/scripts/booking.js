(function(){
	$(document).ready(function(){
		var bookingPage = $(".booking-page");
		var actionButton = bookingPage.find("input[type=submit].action-button");
		var addButton = bookingPage.find(".add-room-button");
		var hiddenRoomAmount = bookingPage.find("input[name=roomrequestAmt]");
		var datePickers = bookingPage.find(".booking-field input[type=text]");
		var roomRequestList = bookingPage.find(".room-request-list");
		var roomRequest = roomRequestList.find(".room-request").first();
		
		var integerInputs = roomRequestList.find("input[type=text]");
		var isExtraBedChBox = roomRequestList.find("input[type=checkbox]");
		
		isExtraBedChBox.off("click").on("click", function(){
			var chb = $(this);
			chb.val(chb.is(":checked"));
		});
		
		integerInputs.off("keypress").on("keypress", function(e){
			if (e.which != 8 && e.which != 46 && (e.which < 49 || e.which > 50)) {
				e.preventDefault();
			}
		});
		datePickers.datepicker({
			dateFormat: "yy-mm-dd"
		});
		
		addButton.click(function(){
			var currentRoomAmt = getNextRoomAmt();
			if(currentRoomAmt >= 10){
				showError();
				return false;
			}
			var rr = roomRequest.clone(true).addClass("clone");
			
			var input = rr.find("input[type=text]");
			var checkBox = rr.find("input[type=checkbox]");
			var select = rr.find("select");
			
			changeName(input, currentRoomAmt).val("");
			changeName(checkBox, currentRoomAmt).prop("checked", false).val("false");
			changeName(select, currentRoomAmt);
			rr.append(createDeleteBtn());
			rr.appendTo(roomRequestList);
			hiddenRoomAmount.val(currentRoomAmt);
		});
		
		function showError(){
			addButton.nextAll(".fe-error").show();
		}
	
		function hideError(){
			addButton.nextAll(".fe-error").hide();
		}
		
		function getCurrentRoomAmt(){
			return roomRequestList.find(".room-request").length;
		}
		
		function getNextRoomAmt(){
			return getCurrentRoomAmt() + 1;
		}
		
		function createDeleteBtn(){
			var $element = $("<span>x</span>").addClass("delete-request");
			$element.click(function(){
				$(this).parents(".room-request").remove();
				hideError();
				hiddenRoomAmount.val(getCurrentRoomAmt());
			});
			return $element;
		}
	});
	
	function changeName($element, currentRoomAmt){
		$element.attr("name", $element.attr("name").slice(0, -1) + currentRoomAmt);
		return $element;
	}
})();