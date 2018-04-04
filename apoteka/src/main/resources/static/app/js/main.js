//var app = angular.module("wafepa", ['ngRoute']);

var app = angular.module("apoteka", ['ngRoute']);

app.controller("lekoviCtrl", function($scope, $http, $routeParams, $location) {
	var id = $routeParams.id;
	var baseUrlLekWithId = "/api/apoteke/";
	var baseUrlLek = "/api/lekovi";
	var baseUrlPr = "/api/proizvodjaci";
	var baseUrlAp = "/api/apoteke";
	
	$scope.lekovi = [];
	$scope.proizvodjaci = [];
	$scope.apoteke = [];
	$scope.newLek = {};
	$scope.newLek.naziv = "";
	$scope.newLek.generickiNaziv = "";
	$scope.newLek.kolicina = "";
	$scope.newLek.cena = "";
	$scope.newLek.proizvodjacId = "";
	$scope.newLek.apotekaId = "";
	
	$scope.search = {};
	$scope.search.apotekaId = "";
	$scope.search.naziv = "";
	
	$scope.pageNum = 0;
	$scope.totalPage = 1;
	
	var getLekoviId = function() {
		$http.get(baseUrlLekWithId + id + "/lekovi").then(
				function uspeh(res){
					$scope.lekovi = res.data;
				},
				function neuspeh(res){
					alert("Something went wrong!");
				}
		);
	}
	
	var getLekovi = function() {
		var config = { params: {}};
		if($scope.search.apotekaId != "") {
			config.params.apotekaId = $scope.search.apotekaId;
		}
		
		if($scope.search.naziv != "") {
			config.params.naziv = $scope.search.naziv;
		}
		
		config.params.page = $scope.pageNum;
		
		$http.get(baseUrlLek, config).then(
				function uspeh(res){
					$scope.lekovi = res.data;
					$scope.totalPage = res.headers("totalPages");
				},
				function neuspeh(res){
					alert("Something went wrong!");
				}
		);
	}
	
	var getProizvodjaci = function() {
		$http.get(baseUrlPr).then(
				function uspeh(res){
					$scope.proizvodjaci = res.data;
				},
				function neuspeh(res){
					alert("Something went wrong!");
				}
		);
	}
	
	var getApoteke = function() {
		$http.get(baseUrlAp).then(
				function uspeh(res){
					$scope.apoteke = res.data;
				},
				function neuspeh(res){
					alert("Something went wrong!");
				}
		);
	}
	
	getLekovi();
	getProizvodjaci();
	getApoteke();
	
	$scope.add = function(apotekaId){
		$http.post(baseUrlLekWithId + apotekaId + "/lekovi", $scope.newLek).then(
			function success(res){
				getLekovi();
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	$scope.brisanje = function(lekId, apotekaId) {
		$http.delete(baseUrlLekWithId + apotekaId + "/lekovi/" + lekId).then(
				function success(res){
					getLekovi();
				},
				function error(res){
					alert("Something went wrong!");
				}
		);
	}
	
	$scope.goToEdit = function(lekId, apotekaId) {
		$location.path("/" + apotekaId + "/lekovi/edit/" + lekId);
	}
	
	$scope.searchLekovi = function() {
		getLekovi();
	}
	
	$scope.ponistiSearchLekovi = function() {
		$scope.search.apotekaId = "";
		$scope.search.naziv = "";
		getLekovi();
	}
	
	$scope.go = function(direction) {
		$scope.pageNum = $scope.pageNum + direction;
		getLekovi();
	}
	
	$scope.goToKupi = function(idLek, apotekaId) {
		$location.path("/" + apotekaId + "/lekovi/kupi/" + idLek);
	}
});

app.controller("editLekCtrl", function($scope, $http, $location, $routeParams) {
	var id = $routeParams.id;
	var apotekaId = $routeParams.apotekaId;
	var baseUrl = "/api/apoteke/";
	var baseUrlPr = "/api/proizvodjaci";
	var baseUrlAp = "/api/apoteke";
	
	$scope.editLek = {};
	$scope.editLek.naziv = "";
	$scope.editLek.generickiNaziv = "";
	$scope.editLek.kolicina = "";
	$scope.editLek.proizvodjacId = "";
	$scope.editLek.apotekaId = "";
	
	var getProizvodjaci = function() {
		$http.get(baseUrlPr).then(
				function uspeh(res){
					$scope.proizvodjaci = res.data;
				},
				function neuspeh(res){
					alert("Something went wrong!");
				}
		);
	}
	
	var getApoteke = function() {
		$http.get(baseUrlAp).then(
				function uspeh(res){
					$scope.apoteke = res.data;
				},
				function neuspeh(res){
					alert("Something went wrong!");
				}
		);
	}
	
	getProizvodjaci();
	getApoteke();
	
	var getLek = function() {
		$http.get(baseUrl + apotekaId + "/lekovi/" + id).then(
				function success(res){
					$scope.editLek = res.data;
				},
				function error(res){
					alert("Something went wrong!");
				}	
		);
	}
	
	getLek();
	
	$scope.edit = function(apotekaId) {
		$http.put(baseUrl + apotekaId + "/lekovi/" + id, $scope.editLek).then(
				function success(res){
					$location.path("/lekovi");
				},
				function error(res){
					alert("Something went wrong!");
				}
		);
	}
});

app.controller("kupiLekCtrl", function($scope, $http, $location, $routeParams) {
	
	var id = $routeParams.id;
	var apotekaId = $routeParams.apotekaId;
	var baseUrlLek = "api/apoteke/";
	
	$scope.lek = {};
	$scope.lek.naziv = "";
	$scope.lek.generickiNaziv = "";
	$scope.lek.kolicina = "";
	$scope.lek.proizvodjacIme = "";
	$scope.lek.apotekaIme = "";
	
	$scope.kupljenaKolicina = "";
	
	var getLek = function() {
		$http.get(baseUrlLek + apotekaId + "/lekovi/" + id).then(
				function success(res){
					$scope.lek = res.data;
				},
				function error(res){
					alert("Something went wrong!");
				}
		);
	}
	
	getLek();
	
	$scope.kupi = function(kupljenaKolicina) {
		var config = { params: {}};
		if($scope.kupljenaKolicina != "") {
			config.params.kolicina = $scope.kupljenaKolicina;
		}
		$http.put(baseUrlLek + apotekaId + "/lekovi/" + id, $scope.lek, config).then(
				function success(res){
					$location.path("/lekovi");
				},
				function error(res){
					alert("Something went wrong!");
				}
		);
	}
	
})

app.service("activitiesSrvc", function($http) {
	
	this.baseUrl = "/api/activities";
	
	this.getActivities = function() {
		var promise = $http.get(this.baseUrl);
		
		return promise;
	}
});

app.controller("ctrl", function ($scope){
	
	$scope.appName = "Apoteka";

});

app.controller("activitiesCtrl", function($scope, $http, $location, activitiesSrvc){
	
	var baseUrl = "/api/activities";
	
	$scope.activities = [];
	
	var getActivities = function(){
		
//		var promise = $http.get(baseUrl);
		var promise = activitiesSrvc.getActivities();
		
		promise.then(
			function uspeh(res){
				$scope.activites = res.data;
				
			},
			function neuspeh(res){
				alert("Something went wrong!");
			}
		);
	}
	
	getActivities();
		
	$scope.goToAdd = function(){
		$location.path("/activities/add");
	}
	
	$scope.goToEdit = function(id){
		$location.path("/activities/edit/" + id);
	}
	
	$scope.delete = function(id){
		
		var promise = $http.delete(baseUrl + "/" + id);
		promise.then(
			function success(res){
				getActivities();
			},
			function error(res){
				alert("Something went wrong!");
			}
		)
	}
	
});

app.controller("addActivityCtrl", function($scope, $http, $location){
	
	var baseUrl = "/api/activities";
	
	$scope.newActivity = {};
	$scope.newActivity.name = "";
	
	$scope.add = function(){
		
		$http.post(baseUrl, $scope.newActivity).then(
			function success(res){
				$location.path("/activities");
			},
			function error(res){
				alert("Something went wrong.");
			}
		);
		
	}
	
});

app.controller("editActivityCtrl", function($scope, $http, $routeParams, $location){
	
	//console.log($routeParams);
	var id = $routeParams.aid;
	var baseUrl = "/api/activities/";
	
	$scope.proizvodjaci = [];
	$scope.apoteke = [];
	$scope.oldActivity = {};
	$scope.oldActivity.name = "";
	
	var getActivity = function(){
		
		$http.get(baseUrl + id).then(
			function success(res){
				$scope.oldActivity = res.data;
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	getActivity();
	
	$scope.edit = function(){
		$http.put(baseUrl + id, $scope.oldActivity).then(
			function success(res){
				$location.path("/activities");
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
});


app.controller("standoviCtrl", function($scope, $http){
	
	var baseUrlSt = "/api/standovi";
	var baseUrlSajam = "/api/sajmovi";
	
	$scope.standovi = [];
	$scope.sajmovi = [];
	
	$scope.noviStand = {};
	$scope.noviStand.zakupac = "";
	$scope.noviStand.povrsina = "";
	$scope.noviStand.sajamId = "";
	
	$scope.searchStand = {};
	$scope.searchStand.zakupac = "";
	$scope.searchStand.minP = "";	
	$scope.searchStand.maxP = "";
	
	$scope.pageNum = 0;
	$scope.totalPage = 1;
	$scope.showForm = true;
	$scope.showEditButton = false;
	$scope.idStand = "";
	
	$scope.toggleForm = function() {
		$scope.showForm = !$scope.showForm;
	}
	
	$scope.searchMinP = function() {
		$scope.searchStand.minP = 100;
		getStandovi();
	}
	
	$scope.edit = function(standId) {
		console.log(standId);
		$scope.noviStand.id = standId;
		$http.put(baseUrlSt + "/" + standId, $scope.noviStand).then(
				function success(res){
					$scope.noviStand.id = "";
					$scope.noviStand.zakupac = "";
					$scope.noviStand.povrsina = "";
					$scope.noviStand.sajamId = "";
					$scope.showEditButton = false;
					getStandovi();
				},
				function error(res){
					alert("Something went wrong!");
				}	
		);
	}
	
	$scope.showButton = function(standId) {
		$scope.idStand = standId;
		$scope.showEditButton = true;
		$http.get(baseUrlSt + "/" + standId).then(
				function success(res){
					$scope.noviStand.zakupac = res.data.zakupac;
					$scope.noviStand.povrsina = res.data.povrsina;
					$scope.noviStand.sajamId = res.data.sajamId;
				},
				function error(res){
					alert("Something went wrong!");
				}	
		);
	}
	
	var getStandovi = function(){
		
		var config = { params: {}};
		if($scope.searchStand.zakupac != "") {
			config.params.zakupac = $scope.searchStand.zakupac;
		}
		
		if($scope.searchStand.minP != "") {
			config.params.minP = $scope.searchStand.minP;
		}
		
		if($scope.searchStand.maxP != "") {
			config.params.maxP = $scope.searchStand.maxP;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		$http.get(baseUrlSt, config).then(
			function success(res){
				$scope.standovi = res.data;
				$scope.totalPage = res.headers("totalPages");
				console.log(res.headers("totalPages") + " pages");
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	var getSajmovi = function(){
		$http.get(baseUrlSajam).then(
			function success(res){
				$scope.sajmovi = res.data;
				console.log($scope.sajmovi);
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	getStandovi();
	getSajmovi();
	
	$scope.add = function(){
//		$scope.noviStand.id = null;
		$http.post(baseUrlSt, $scope.noviStand).then(
			function success(res){
				getStandovi();
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	$scope.changeHappened = function(){
		alert("It did!");
	}
	
	$scope.search = function() {
		getStandovi();
	}
	
	$scope.go = function(direction) {
		$scope.pageNum = $scope.pageNum + direction;
		getStandovi();
	}
});

app.controller("usersCtrl", function($scope, $http, $location) {
	
	var baseUrl = "/api/users";
	
	$scope.users = [];
	$scope.searchValue = "";
	
	var promise = $http.get(baseUrl);
	
	var getUsers = function(){
		var config = { params: {}};
		console.log($scope.searchValue);
		if($scope.searchValue != "") {
			config.params.name = $scope.searchValue;
		}
		
		var promise = $http.get(baseUrl, config);
		
		promise.then(
			function uspeh(res){
				$scope.users = res.data;
				console.log($scope.users);
			},
			function neuspeh(res){
				$location.path("/error");
			}
		);
		
		console.log("Test");
	}
	
	getUsers();
	
	$scope.goToAdd = function() {
		$location.path("/users/add");
	};
	
	$scope.goToEdit = function(id) {
		$location.path("/users/edit/" + id);
	};
	
	$scope.deleteUser = function(id) {
		console.log(id + " id user");
		console.log(baseUrl + "/" + id);
		$http.delete(baseUrl + "/" + id).then(
				function uspeh(res){
					console.log("User response");
					getUsers();
				},
				function neuspeh(res){
					$location.path("/error");
				}
		);
	};
	
	$scope.search = function() {
		getUsers();
	}
});

app.controller("addUserCtrl", function($scope, $http, $location) {
	
	var baseUrl = "/api/users";
	
	$scope.newUser = {};
	$scope.newUser.firstname = "";
	$scope.newUser.lastname = "";
	$scope.newUser.email = "";
	$scope.newUser.password = "";
	$scope.newUser.passwordConfirm = "";
	
	$scope.add = function(){
		$http.post(baseUrl, $scope.newUser).then(
			function success(res){
				$location.path("/users");
			},
			function error(res){
				$location.path("/error");
			}
		);
		
	}
});

app.controller("editUserCtrl", function($scope, $http, $location, $routeParams) {
	console.log($routeParams.id)
	var id = $routeParams.id;
	var baseUrl = "/api/users/";
	
	$scope.editUser = {};
	$scope.editUser.firstname = "";
	$scope.editUser.lastname = "";
	$scope.editUser.email = "";
	$scope.editUser.password = "";
	$scope.editUser.passwordConfirm = "";
	
	var getUser = function(){
		
		$http.get(baseUrl + id).then(
			function success(res){
				console.log(res.data + " this user to edit");
				$scope.editUser = res.data;
			},
			function error(res){
				$location.path("/error");
			}
		);
	}
	
	getUser();
	
	$scope.edit = function(){
		$http.put(baseUrl + id, $scope.editUser).then(
			function success(res){
				$location.path("/users");
			},
			function error(res){
				$location.path("/error");
			}
		);
	}
});


app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
			controller: 'ctrl'
		})
		.when('/apoteke', {
			templateUrl : '/app/html/apoteke.html'
		})
		.when('/apoteke/:id/lekovi', {
			templateUrl : '/app/html/lekovi.html'
		})
		.when('/lekovi', {
			templateUrl : '/app/html/lekovi.html'
		})
		.when('/:apotekaId/lekovi/kupi/:id', {
			templateUrl : '/app/html/kupi-lek.html'
		})
		.when('/:apotekaId/lekovi/edit/:id', {
			templateUrl : '/app/html/edit-lek.html'
		})
		.when('/activities', {
			templateUrl : '/app/html/activities.html'
		})
		.when('/activities/add', {
			templateUrl : '/app/html/add-activity.html'
		})
		.when('/activities/edit/:aid', {
			templateUrl : '/app/html/edit-activity.html'
		})
		.when('/standovi', {
			templateUrl : '/app/html/standovi.html'
		})
		.when('/users', {
			templateUrl : '/app/html/users.html'
		})
		.when('/users/add', {
			templateUrl : '/app/html/add-user.html'
		})
		.when('/users/edit/:id', {
			templateUrl : '/app/html/edit-user.html'
		})
		.when('/error', {
			templateUrl : '/app/html/alert.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);

