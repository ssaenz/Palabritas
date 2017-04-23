function loadWordCloud(words) {

	$('#wordcloud').jQCloud(words, {
		//width: 300,
		height: 500,
		autoResize: true
	});

	var save;
	var myhtml;
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
			myhtml += '<span class="list-pos">'+$(this).data("pos")+'</span>';
			myhtml += '<span class="rest"><span class="list-word">'+$(this).text()+'</span><br><span class="list-count">'+$(this).data("count")+'</span></span>';
			myhtml += '</div>';
			$(this).after(myhtml);
		} },'> span');
	$('#wordcloud').on({
		mouseleave: function () {
			$(this).remove();
		}
	},'.cloudExpand');
}

$(document).ready(function() {

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
		}
	});
});

$(function() {

	initialLoad();
	changeView();

});

function changeView() {

	$('.btn-list').on('click', function() {

		getSortedWords(0, 10, function(words) {
			renderWords(words, 'list');
		});

		return false;
	});

	$('.btn-cloud').on('click', function() {

		getSortedWords(0, 30, function(words) {
			renderWords(words, 'cloud');
		});

		return false;
	});

}

/** INITIAL LOAD OF DATA **/
// Initial load of the page will render a list with the first most 10 used words
function initialLoad() {

	var offset = 0;
	var limit = 20;
	var view = 'list'; // Future views can include [list, tagcloud, history...]
	getSortedWords(offset, limit, function(words) {
		renderWords(words, view);
	});

}

/** GET SORTED WORDS **/
function getSortedWords(offset, limit, callback) {

	var urlBase = 'http://localhost:8081/palabritas-service';
	var url = urlBase + '/commitwords';
	//url = '/mock.json';
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
	$listView.html($wordListHtml);

	$('.list-view').show();
	$('#wordcloud').hide();
	$('.btn-list').addClass('active');
	$('.btn-cloud').removeClass('active');
}

function renderWordsInCloud(words) {

	if ($('#wordcloud').length > 0) {
		var words = parseWords(words);
		loadWordCloud(words);
	}

	$('.list-view').hide();
	$('#wordcloud').show();
	$('.btn-cloud').addClass('active');
	$('.btn-list').removeClass('active');
}

function parseWords(words) {

	parsedWords = [];
	words.forEach(function(word, index) {

		var parsedWord = {};
		parsedWord.text = word.word;
		parsedWord.weight = word.occurrences;
		console.log(word, parsedWord);
		parsedWords.push(parsedWord);
	});

	return parsedWords;

}