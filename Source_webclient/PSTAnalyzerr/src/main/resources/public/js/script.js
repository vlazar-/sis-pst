$(function () {
	/*$('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
	$('.tree li.parent_li > span').on('click', function (e) {
		var children = $(this).parent('li.parent_li').find(' > ul > li');
		if (children.is(":visible")) {
			children.hide('fast');
			$(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
		} else {
			children.show('fast');
			$(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
		}
		e.stopPropagation();
	});*/
});

/*template = "{{#single}} <div class=\"row\"> <div class=\"col-md-2 text-right\">" +
		   "<p><b>Subject: </b></p> <p><b>From: </b></p> <p><b>To: </b></p> <p><b>"+
		   "Date: </b></p> <p><b>Folder: </b></p> <p><b>Attachments: </b></p> </div> "+
		   "<div class=\"col-md-10\"><p>dsadsa</p><p>dasdas</p><p>dasdas</p><p>asdsa</p><p>adasd</p><p>adasd</p>" +
		   "</div> </div> <div class=\"row\"> <p>{{emailBody}}</p> </div>{{/single}}";*/


$(document).ready(function() {
	$(".getSingle").on("click", function(e){
		e.preventDefault();
		path = $(this).attr("href");

		$.ajax({
			type: "get",
			url: path,
			success : function(data)
			{
				$("#single").html(data);
			}
		});
	});
});