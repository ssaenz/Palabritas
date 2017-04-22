$(function() {

	initialLoad();

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
	var url = urlBase + '/words';
	var data = {};
	data.offset = offset;
	data.limit = limit;

	$.get(url, data, function(data) {

		callback(data.words);

	});
}

// View dispatcher and word renderer
function renderWords(words, view) {

	// The render of the words will depend on the HTML structure

}