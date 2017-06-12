(function() {
    var reservationList = [];
    $(document).ready(function () {
        $(".reject-btn").click(function () {
            var requestId = $(this).data("request-id");
            $.ajax({
                url: "FrontController",
                data: {
                    command: "decline",
                    requestId: requestId
                },
                success: function () {
                    $("input[name=command]").val("tomain");
                    appointForm.submit();
                },
                error: function () {
                    $("input[name=command]").val("tomain");
                    appointForm.submit();
                }
            });
        });

        $(".bill-btn").click(function () {
            var requestId = $(this).data("request-id");
            $.ajax({
                url: "FrontController",
                data: {
                    command: "bill",
                    reservations: JSON.stringify(reservationList)
                },
                success: function (billId) {
					$("input[name=billId]").val(billId);
					$("input[name=command]").val("showbill");
					appointForm.submit();
                },
                error: function () {
                    $("input[name=command]").val("tomain");
                    appointForm.submit();
                }
            });
        });

        $(".appointment-button").click(function(){
		var requestId = $(this).data("room-request-id");
        $.ajax({
            url : "FrontController",
            data : {
            	command : "approve",
                requestId : requestId
            },
            success : function(roomList) {
					var table = $(".room-window").find("tbody");
					table.empty();
					var filteredList = roomList.filter(function (room) {
                        var reservedRooms = reservationList.filter(function (reserv) {
							return reserv.fkRoom.id == room.id;
                        });
                        return reservedRooms.length == 0;
                    });
					if(filteredList.length == 0){
						showErrorMessage();
						return;
					}
					$.each(filteredList,function (i, room) {
						$('<tr/>').appendTo(table)
							.append($('<td/>').text(room.roomNumber))
							.append($('<td/>').text(room.square))
							.append($('<td/>').text(room.floor))
							.append($('<td/>').append(getIcon(room.cityView)))
							.append($('<td/>').append(getIcon(room.balcony)))
							.append($('<td/>').append(getIcon(room.extraBed)))
							.append($('<td/>').text(room.fkBedtype.type))
							.append($('<td/>').text(room.fkBedtype.pricePlusAmt))
							.append($('<td/>').text(room.fkRating.ratingName))
							.append($('<td/>').text(room.fkRating.price))
							.append($('<td/>').text(room.fkRating.priceForExtraBed))
							.append($('<td/>').append(getAppointButton(room.id, requestId)));
					});
					showPopupWindow();
				}
			});
		});
	
	$(".window-background").click(function(){
		hidePopupWindow();
	});
	
	function getAppointButton(roomId, requestId){
		var button = $("<input type='button'/>").addClass("action-button").val("Approve")
		.data("room-id", roomId)
		.click(function(){
			var currentRoomId = $(this).data("room-id");
			
			reservationList.push({
				fkRoomrequest: {id : requestId},
				fkRoom: {id : currentRoomId}
			});
			$(".appointment-button[data-room-request-id="+ requestId +"]").hide();
			hidePopupWindow();
		});
		return button;
	}
	var requestId = 6;
	$(".ajax-window").find(".action-button").click(function(){
			var currentRoomId = $(this).data("room-id");
			
			reservationList.push({
				fkRoomrequest: {id : requestId},
				fkRoom: {id : currentRoomId}
			});
			$(".appointment-button[data-room-request-id="+ requestId +"]").hide();
			hidePopupWindow();
		});
	
	function getIcon(boolFlag){
		var icon = $("<span/>").addClass(boolFlag +"-icon")
		return icon;
	}

        $(window).on("resize", changePopupWindowPosition);
    });

    function showPopupWindow() {
        $(".window-background, .ajax-window").show();
        changePopupWindowPosition();
    }

    function changePopupWindowPosition() {
        var ajaxWindow = $(".ajax-window");
        var wHeight = $(window).height();
        var wWidth = $(window).width();
        ajaxWindow.css({
            top: wHeight / 2 - ajaxWindow.outerHeight() / 2,
            left: wWidth / 2 - ajaxWindow.outerWidth() / 2
        });
    }

    function hidePopupWindow() {
        $(".window-background, .ajax-window").hide();
    }

    function showErrorMessage() {
		$(".fe-error").show();
    }

})();