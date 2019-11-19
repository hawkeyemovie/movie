$(document).ready(function() {

	//加载布局
	$.getJSON("http://192.168.121.1:8080/shop/getLayout?layoutNum=lo11", function(data) {
		$(".seats-wrapper").empty();

		var div = "<div class='row'></div>";
		var layout = String(data.layout);
		var column = parseInt(data.column);

		var row;

		var arr = layout.split('-');
		var r = 1;
		var c = 0;

		for(j = 0; j < arr.length; j++) {
			chlayout += arr[j];

			c++;
			console.log(arr[j]);
			if(arr[j] == 0) {
				row += "<span class='seat empty' data=" + r + "-" + c + "></span>";
			}
			if(arr[j] == 1) {
				row += "<span class='seat selectable' onclick='soldf();' data=" + r + "-" + c + "></span>";
			}
			if(arr[j] == 2) {
				row += "<span class='seat sold' data=" + r + "-" + c + "></span>";
			}
			if(0 == c % column) {
				$(".seats-wrapper").append("<div class='row'>" + row + "</div>");
				row = "";
				r++;
				c = 0;
			}
		}
	});

	$(".ok-btn,.btn").click(function() {
		$(".modal-container").hide();
	});

	var span = "<span class='iLogincomp-count-down'><span id='iLoginCounDown'>60</span>s</span>";

	$("#iloginBtn").click(function() {
		var phoneNumInput = $("#phoneNumInput").val();
		var codeInput = $("#codeInput").val();
		var ticketPrice = $(".price").text();
//		alert(ticketPrice);

		if(phoneNumInput.trim().length != 11) {
			$(".modal-container").show();
			$(".tip").html("请输入11位正确的手机号码");
			return;
		}
		if(codeInput.trim().length != 6) {
			$(".modal-container").show();
			$(".tip").html("请输入6位正确的手机验证码");
			return;
		}

		var url = "http://192.168.121.1:8080/shop/gender";
		$.get(url, {
			"cId": 1,
			"cfId": 1,
			"chId": 1,
			"mId": 1,
			"seatName": chseatName,
			"column": 13,
			"ticketPrice":ticketPrice,
			"yzm": codeInput,
			"phoneNumbers": phoneNumInput,
			"layout": chlayout
		}, function(data) {
			if(data != "false"){
				console.log(data);
				window.location.href = "order-confirm.html?order="+data
			}
		});
	});

	$("#phoneNumInput").bind("input", function() {
		if(11 == String($("#phoneNumInput").val()).length) {
			$("#sendCodeBtn").addClass("iLogincomp-active");
			$("#sendCodeBtn").bind("click", function() {
								$.getJSON("http://192.168.121.1:8080/login/smssingle?phoneNumbers=" + $("#phoneNumInput").val(), function(data) {
									console.log(data);
									if(data == true) {
										$("#sendCodeBtn").html(span);
										ss = 60;
										myVar = setInterval(function() {
											$("#iLoginCounDown").text(--ss);
											if(ss == 0) {
												myStopFunction();
												$("#sendCodeBtn").html("获取验证码");
											}
										}, 1000);
									}
								})
			});
		} else {
			$("#sendCodeBtn").removeClass("iLogincomp-active");
			$("#sendCodeBtn").unbind("click");
		}
	});
});

var chlayout = "";

var chseatName = "{";

var ss = 60;

var myVar;

function soldf() {
	var that = event.currentTarget;

	console.log($(".selected").length);
	$(that).toggleClass("selected");
	$(that).toggleClass("selectable");
	var price = parseInt($(".show-info").children("div:last-child").children("span:last-child").text().substr(1, 2));
	$(".ticket-container").empty();

	chseatName = "{"

	$(".no-ticket").hide();
	$(".has-ticket").show();

	$("#iloginBtn").removeClass("disable");

	if($(".selected").length == 0) {
		$(".has-ticket").hide();
		$(".no-ticket").show();
		$("#iloginBtn").addClass("disable");
		$(".price").text(0 * price);
	}

	if($(".selected").length > 4) {
		console.log($(".selected").length);
		$(that).removeClass("selected");
		$(".modal-container").show();
		$(".tip").html("一次最多购买4张票");
	}

	$(".selected").each(function(t) {
		var str = $(".selected").eq(t).attr("data").split('-');

		if($(".selected").length == (t + 1)) {
			chseatName += t + ":'" + str[0] + "排" + str[1] + "座'}"
		} else {
			chseatName += t + ":'" + str[0] + "排" + str[1] + "座',"
		}

		$(".price").text((t + 1) * price);

		$(".ticket-container").append("<span class='ticket' data-row-id='" + str[0] + "' data-column-id='" + str[1] + "' data-index='" + str[0] + "-" + str[1] + "'>" + str[0] + "排" + str[1] + "座</span>");
	})

}

function myStopFunction() {
	clearInterval(myVar);
}