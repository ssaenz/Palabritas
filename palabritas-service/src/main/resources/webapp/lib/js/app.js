$(function() {

	initialLoad();
	generateMockJson();

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


function generateMockJson() {

	var jsonArray = [];
	jsonArray.push({word:'Fix', occurrences:1500});
	jsonArray.push({word:'Bug', occurrences:1400});
	jsonArray.push({word:'Remove', occurrences:1400});
	jsonArray.push({word:'Password', occurrences:1400});
	jsonArray.push({word:'Caranchoa', occurrences:1400});
	jsonArray.push({word:'Yaya', occurrences:1400});
	jsonArray.push({word:'Palabritas', occurrences:1400});

	console.log(JSON.stringify(jsonArray));

}