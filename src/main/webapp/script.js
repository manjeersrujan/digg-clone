// create the module and name it scotchApp
var scotchApp = angular.module('scotchApp', [ 'ngRoute', 'restangular' ]);

// configure our routes
scotchApp.config(configure);
configure.$inject = [ '$httpProvider', 'RestangularProvider' ];
/* @ngInject */
function configure($httpProvider, RestangularProvider) {
	// RestangularProvider.setBaseUrl(configEndPoints().baseUrl.url);
	RestangularProvider.setFullResponse(true);
	RestangularProvider.setDefaultHeaders({
		'Content-Type' : 'application/json',
		'X-Requested-With' : 'XMLHttpRequest'
	});
}

scotchApp.config(function($routeProvider, $locationProvider) {

	$routeProvider

	// route for the home page
	.when('/', {
		templateUrl : 'pages/home.html',
		controller : 'mainController'
	})

	.when('/all-topics', {
		templateUrl : 'pages/all-topics.html',
		controller : 'allTopicsController'
	})

	// route for the about page
	.when('/create-topic', {
		templateUrl : 'pages/create-topic.html',
		controller : 'creaeTopicController'
	});
});


function renderTopicTable(topicArray, tableName, sortCol, sortOrder){
	
	var dataSet = new Array(topicArray.length);
	for (i in topicArray) {
		var topic = topicArray[i]
		var topicRow = new Array(8);
		topicRow[0] = topic.id;
		topicRow[1] = topic.title;
		topicRow[2] = topic.createdBy;
		topicRow[3] = topic.content;
		if (topic.voteCount && topic.voteCount.UPVOTE) { topicRow[4] = topic.voteCount.UPVOTE; } 
		else { topicRow[4] = 0; }

		if (topic.voteCount && topic.voteCount.DOWNVOTE) { topicRow[5] = topic.voteCount.DOWNVOTE; } 
		else { topicRow[5] = 0; }
		topicRow[6] = '<button  onclick=\'javascript:vote('+topic.id+', "UPVOTE");\' >Upvote</a>';
		topicRow[7] = '<button  onclick=\'javascript:vote('+topic.id+', "DOWNVOTE");\'>Downvote</a>';
		dataSet[i] = (topicRow);
	}
	$('#'+tableName).DataTable({
		data : dataSet,
		columns : [ { title : "ID" }, 
		            { title : "Title" }, 
		            { title : "CreatedBy" }, 
		            { title : "Content" }, 
		            { title : "Up Votes" }, 
		            { title : "Down Votes" } , 
		            { title : "Upvote Topic" }, 
		            { title : "Downvote Topic" }
		          ],
		order : [ [ sortCol, sortOrder ] ],
		destroy: true
	}).page.len( 50 ).draw();
	
	
}


function renderPopularTopics(){
	globHttp({
		method : 'GET',
		url : 'popularTopics',
		headers : {
			'Content-Type' : 'application/json'
		}
	}).then(function successCallback(response) {
		renderTopicTable(response.data.payload.populorTopics, 'popularTopics', 4, 'desc')
	}, function errorCallback(response) {
		console.log(response);
	});
}


function renderAllTopics(){
	globHttp({
		method : 'GET',
		url : 'allTopics',
		headers : {
			'Content-Type' : 'application/json'
		}
	}).then(function successCallback(response) {
		renderTopicTable(response.data.payload.topics, 'allTopics', 0, 'asc');
	}, function errorCallback(response) {
		console.log(response);
	});
}

// create the controller and inject Angular's $scope
scotchApp.controller('mainController', function($scope, $http) {
	globHttp = $http
	renderPopularTopics();
});

scotchApp.controller('allTopicsController', function($scope, $http) {
	globHttp = $http;
	renderAllTopics();
});

function vote (id, voteType) {
	globHttp({
		method : 'POST',
		url : 'vote',
		params: {topicId: id, voteType: voteType},
		headers : {
			'Content-Type' : 'application/json'
		}
	}).then(function successCallback(response) {
		console.log(response.data)
	}, function errorCallback(response) {
		console.log(response);
	});
	renderPopularTopics();
	renderAllTopics();
};


scotchApp.controller('creaeTopicController', function($scope, $http) {
	globHttp = $http
	$scope.message = '';
	$scope.submitForm = function() {

		$http({
			method : 'POST',
			url : 'topic',
			data : angular.toJson($scope.topicForm),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(
				function successCallback(response) {
					$scope.message = "Topic created with ID: "
							+ response.data.payload.topicId;
					$scope.topicForm.$setPristine();
				}, function errorCallback(response) {
					console.log(response)
					$scope.message = "Failed : " + response.statusMessage;
				});
	};
});