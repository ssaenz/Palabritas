var words = [
  {text: "Lorem", weight: 13, html: {class: "hoverClass", "data-pos": "1", "data-count": "1000"}},
  {text: "Ipsum", weight: 10.5},
  {text: "Dolor", weight: 9.4},
  {text: "Sit", weight: 8},
  {text: "Amet", weight: 6.2},
  {text: "Consectetur", weight: 5},
  {text: "Adipiscing", weight: 5}
];

$(document).ready(function() {
	$('#wordcloud').jQCloud(words, {
	  //width: 300,
	  height: 500,
	  autoResize: true
	});

	var save;
	var myhtml;
	$("#wordcloud_word_0").hover(
	  function() {
	    //cuando está dentro
		console.log("mete");
		save = $(this).clone();
		$(this).text("kowadonga");
	  }, function() {
	    //cuando sale
		console.log("saca");
		$(this).after(save).remove();
	  }
	);
	/*$("#wordcloud").on({
	    mouseenter: function () {
	       	//cuando está dentro
			save = $(this).text();
			myhtml = '<span class="list-content-wrapper cloud-view"><span class="list-pos">3</span><span class="list-word">Palabra clave 3</span><span class="list-count">1100</span></span>';
			$(this).text("").append(myhtml);
	    },
	    mouseleave:function () {
	       	//cuando sale
	       	console.log(save);
			$(this).text(save);
	    }
	},'span');*/
	$('#wordcloud').on({
		mouseenter: function () {
			var bw = parseInt($(this).width());
			var bh = parseInt($(this).height());
			var lpos = parseInt($(this).position().left);
			var tpos = parseInt($(this).position().top);
			var extrapad;
			if($(this).hasClass("w1")) {extrapad = 20;} 
			else if($(this).hasClass("w2") || $(this).hasClass("w3")) {extrapad = 30;} 
			else if($(this).hasClass("w4")) {extrapad = 40;} 
			else {extrapad = 60;}
			var myhtml = '<div class="cloudExpand" style="width: '+(bw+extrapad)+'px; height: '+(bh+extrapad)+'px; position: absolute; left: '+(lpos-(extrapad/2))+'px; top: '+(tpos-(extrapad/2))+'px;z-index: 9999;font-size: '+$(this).css("font-size")+';">';
			myhtml += '<span class="list-pos">1</span>';
			myhtml += '<span class="rest"><span class="list-word">'+$(this).text()+'</span><br><span class="list-count">1300</span></span>';
			myhtml += '</div>';
			$(this).after(myhtml);
		} },'> span');
	$('#wordcloud').on({
		mouseleave: function () {
			$(this).remove();
		}
	},'.cloudExpand');
	
	/* SECREEEET */
	var ftext;
	var audio = new Audio('lib/mp3/secret.mp3');

	$( ".secret" ).on( "click", function() {
		if($("footer + link").length == 0) {
			// a joder corneas!
			$("footer").after('<link rel="stylesheet" type="text/css" href="lib/css/styleshit.css">');
			//setInterval(spectrum('.general-content'),99999999);
			ftext = $("footer .container > p").text();
			$("footer .container > p").text("Powered by GeoCities.");
			audio.play();
		}else{
			// BASTAAAA!
			$("footer + link").remove();
			$("footer .container > p").text(ftext);
			audio.pause();
			audio.currentTime = 0;
			//spectrum();
			//$('.general-content').css("background-color", "#eee");
		}
	});

	function spectrum(id){
		/*var hue = 'rgb(' + (Math.floor(Math.random() * 256)) + ',' + (Math.floor(Math.random() * 256)) + ',' + (Math.floor(Math.random() * 256)) + ')';
    	setInterval(spectrum($(id).animate( { backgroundColor: hue }, 1000),2000));
		if(id == undefined) return false;*/
	}
});

$(function() {

	initialLoad();

	var words = [
		{text: "Lorem", weight: 50},
		{text: "Ipsum", weight: 10.5},
		{text: "Dolor", weight: 9.4},
		{text: "Sit", weight: 8},
		{text: "Amet", weight: 6.2},
		{text: "Consectetur", weight: 5},
		{text: "Adipiscing", weight: 5}
	];

	$('.cloud-view').jQCloud(words, {
		autoResize: true
	});

});

/** INITIAL LOAD OF DATA **/
// Initial load of the page will render a list with the first most 10 used words
function initialLoad() {

	var offset = 0;
	var limit = 10;
	var view = 'list'; // Future views can include [list, tagcloud, history...]
	getSortedWords(offset, limit, function(words) {
		renderWords(words, view);
	});

}

/** GET SORTED WORDS **/
function getSortedWords(offset, limit, callback) {

	var urlBase = 'http://localhost:8081/palabritas-service';
	var url = urlBase + '/commitwords';
	url = '/mock.json';
	var data = {};
	data.offset = offset;
	data.limit = limit;

	$.get(url, data, function(commitWords) {

		callback(commitWords);

	});
}

// View dispatcher and word renderer
function renderWords(words, view) {

	switch (view) {
		case 'list':
			renderWordsInList(words);
			break;
		case 'cloud':
			renderWordsInCloud(words);
		default:
			return false;
	}
}

function renderWordsInList(words) {

	// We first create the HTML structure of the list that is going to be rendered
	var $wordListHtml = '';
	words.forEach(function(commitWord, index) {

		var position = index + 1;
		$wordListHtml += '<li class="list-box"><div class="list-content">' +
		'<span class="list-pos">' + position + '</span>' +
		'<span class="list-word">' + commitWord.word + '</span>' +
		'<span class="list-count">' + commitWord.occurrences + '</span>' +
		'</div></li>';
	});

	// We attach it to the list
	var $listView = $('.list-view');
	$listView.append($wordListHtml);
}