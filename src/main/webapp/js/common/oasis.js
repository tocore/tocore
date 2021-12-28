;(function($) {
	
	/*********************************************************
	 * 
	 * loading bar 표시
	 * 
	 * @returns
	 */
	function showLoading() {
		try {
			$("#loadingBox").show();
			$("#loadingBox_bg_block").show();
		} catch (ex) {}	
	}
	
	/*********************************************************
	 * 
	 * loading bar 숨기기
	 * 
	 * @returns
	 */
	function hideLoading() {
		try {
			$("#loadingBox").hide();
			$("#loadingBox_bg_block").hide();
		} catch (ex) {}
	}
	
	/*********************************************************
	 * 
	 *  spring security ajax filter 처리
	 *  
	 *  spring security 에서 ajax 호출시 아래 token 정보가 필요함
	 *  spring security csrf 값을 전송하기 위해 헤드에 값 설정
	 *
	 ********************************************************/
	$.ajaxPrefilter(function (options) {
  		var token 	= $("meta[name='_csrf']").attr("content");
		var header 	= $("meta[name='_csrf_header']").attr("content");
		if (options.type === 'POST') { 
			options.headers = options.headers || {}; options.headers[header] = token; 
		} 
	});


	/*********************************************************
	 * 
	 *  ajax 호출
	 *  
	 *  파라미터
	 *  	url 		: 
	 *  	params 		: json 형식
	 *  	sucessFun 	: 성공시 처리 할 함수
	 *  	errorFun 	: 실패시 처리 할 함수
	 *  	async 		: 동기화 여부
	 *
	 ********************************************************/
	$.AjaxCall = function(url, params, sucessFun, errorFun, async) {		

	    if (async == undefined) async = true;
	    	    
	    // ****************************************************/
	    // 자바단에서 @requestBody 로 받을수 있게 설정
	    // 1. JSON.stringify(params)
	    // 2. contentType : "application/json; charset=UTF-8"	
	    // ****************************************************/
	    showLoading();	

		$.ajax({
			type		: "POST",
			url			: url,
			data		: JSON.stringify(params),
			async		: async,
			dataType	: 'json',
			contentType : "application/json; charset=UTF-8",			
			success : function(data) {
				if (data.errorCode != undefined && data.errorCode == '401') {
					alert(data.message);
					if(typeof(errorFun) == 'function') errorFun(data);
				 	return;
				}
				if(data.message != undefined && data.message != '') {
				    alert(data.message);
				}
				if(typeof(sucessFun) == 'function') sucessFun(data);
			}, 
			error : function(xhr, status, error){
				alert("시스템 오류가 발생하였습니다." + error);								
				if (typeof(errorFun) == 'function') {
					errorFun();
				}   
				hideLoading();
			},
			complete : function(jqXHR, textStatus) {
				hideLoading();
			}
		});
	}	


	/*********************************************************
	 * 
	 *  폼 validation jquery plugin
	 *  
	 *  파라미터
	 *  	callbackFuc	: 처리 후 호출 할 콜백 함수
	 *  
	 *  ex)
	 *  	<input type="text" validate="required, number, 0over"/>
	 *
	 ********************************************************/
	$.fn.validation = function (callbackFuc) {		
			
		var breakOut = false; 
		var frm = $(this);	
		
		function showAnimation (obj, msg) {
	    	obj.animate({ backgroundColor: '#FFA500' }, 1000);			                	
	    	alert(msg);
	
	        obj.select().focus();
	        var timer = setTimeout(function () {
	        	obj.animate({ backgroundColor: '' }, 2000);
	            clearTimeout(timer);
	        }, 2000);
		}
		
		// 필수 체크
		function requireValidate (obj, validates) {
			var caption = obj.parent().parent().find("th").text();
	        if (validates.indexOf("required") > -1) {
	            if (obj.val() == "") {		           
	            	if (caption != nudefiend) {
	            		showAnimation (obj, "필수 입력 항목입니다.");
	            	} else {
	            		showAnimation (obj, caption + " 은(는) 필수 입력 항목입니다.");
	            	}
	                return false;
	            } else { return true; }
	        } else { return true; }
		}
		
		// 숫자 체크
		function numberValidate (obj, validates) {
			var caption = obj.parent().parent().find("th").text();
	        // 숫자가 아니면
	        if (validates.indexOf("number") > -1) {
	            if (obj.val() == "" || (!$.isNumeric(obj.val().replace(/,/g, '')))) {
	            	if (caption != nudefiend) {
	            		showAnimation (obj, "숫자만 입력 가능합니다.");
	            	} else {
	            		showAnimation (obj, caption + " 은(는) 숫자만 입력 가능합니다.");		
	            	}                   
	                return false;
	            } else { return true; }
	        } else { return true; }
		}				
		
		
		// 0 보다 큰 숫자가 아니면
		function over0Validate (obj, validates) {
			var caption = obj.parent().parent().find("th").text();
	        // 0 보다 큰 숫자가 아니면
	        if (validates.indexOf("0over") > -1) {
	            if (obj.val() == ""
	                || (!$.isNumeric(obj.val().replace(/,/g, '')))
	                || Number(obj.val().replace(/,/g, '')) <= 0) {	
	            	
	            	if (caption != nudefiend) {
	            		showAnimation (obj, "0보다 큰 숫자만 입력 가능합니다.");
	            	} else {
	            		showAnimation (obj, caption + " 은(는) 0보다 큰 숫자만 입력 가능합니다.");	
	            	}                     
	                return false;
	            } else { return true; }
	        } else { return true; }
		}
	
		// 날짜 형식이 아니면
		function dateValidate (obj, validates) {
			var caption = obj.parent().parent().find("th").text();
	        // 날짜 형식이 아니면
	        if (validates.indexOf("date") > -1) {
	            if (obj.val() == "" || !Date.parse(child.val())) {
	            	obj.css("border", "1px solid #dcdcdc");	
	            	if (caption != nudefiend) {
	            		showAnimation (obj, "날짜형식만 가능합니다.");
	            	} else {
	            		showAnimation (obj, caption + " 은(는) 날짜형식만 가능합니다.");
	            	}  	                   
	                return false;
	            } else { return true; }
	        } else { return true; }
		}
					
		$.each(frm[0].elements, function(index, ele) {
			var obj = $(ele);			
			var validates = ($(ele).attr("validate") == undefined)? "" : $(ele).attr("validate");
			if (ele.id != null && ele.id != "" && validates != "") {
				// console.log(ele.id + ":" + validates);
				if (! requireValidate(obj, validates)) { 
					if (typeof callbackFuc === "function") {
						callbackFuc(this);
					}
					breakOut=true; return false; 
				}
				if (! numberValidate(obj, validates)) { 
					if (typeof callbackFuc === "function") {
						callbackFuc(this);
					}
					breakOut=true; return false; 
				}
				if (! over0Validate(obj, validates)) { 
					if (typeof callbackFuc === "function") {
						callbackFuc(this);
					}
					breakOut=true; return false; 
				}
				if (! dateValidate(obj, validates)) { 
					if (typeof callbackFuc === "function") {
						callbackFuc(this);
					}
					breakOut=true; return false; 
				}
			}				    
		});
		
	    if (breakOut)
	        return false;
	    else return true;
	}


	/********************************************************
	 *  
	 *  jquery plug in form serializeObject
	 *  
	 *  파라미터:
	 *  
	 *  ex)
	 *  	$("#form").serializeObject();
	 * 
	 ********************************************************/
	$.fn.serializeObject = function() { 
		var obj = null; 
		try { 
			if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) { 
				var arr = this.serializeArray(); 
				if (arr) { 
					obj = {}; 
					$.each(arr, function() {obj[this.name] = this.value; }); 
				}
			} 
		} catch(e) { 
			alert(e.message); 
		} finally {} 	
		return obj; 
	}
})(jQuery);

