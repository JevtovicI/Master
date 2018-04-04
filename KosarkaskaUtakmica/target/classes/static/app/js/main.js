var app = angular.module("wafepa", ['ngRoute']);

app.controller("ctrl", function ($scope){
	
	$scope.appName = "Wafepa";

});

app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
			controller: 'ctrl'
		})
		.when('/igraci', {
			templateUrl : '/app/html/igraci.html'
		}).when('/igraci/edit/:id',{
	        templateUrl: '/app/html/edit-igrac.html'
	    })
		.otherwise({
			redirectTo: '/'
		});
}]);


app.controller("igraciCtrl", function($scope, $http, $location){
	
	var baseUrlIg = "/api/igraci";
	var baseUrlTimovi = "/api/timovi";
	var baseUrlPozicije = "/api/pozicije";
	
	$scope.pageNum = 0;
    $scope.totalPages = 0;
	
	$scope.igraci = [];
	$scope.timovi = [];
	$scope.pozicije = [];
	
	
	
	$scope.noviIgrac = {};
	$scope.noviIgrac.imePrezime = "";
	$scope.noviIgrac.broj = "";
	$scope.noviIgrac.licneGreske = "";
	$scope.noviIgrac.van = "";
	$scope.noviIgrac.pozicijaId = "";
	$scope.noviIgrac.timId = "";
	
	
	
	$scope.trazeniIgrac={};
	$scope.trazeniIgrac.imePrezime ="";
	$scope.trazeniIgrac.broj ="";
	$scope.trazeniIgrac.timId ="";	
	
	
	var getIgraci = function(){
		
		var config = { params:{}};
		
		config.params.pageNum = $scope.pageNum;
		
		  if($scope.trazeniIgrac.imePrezime != ""){
	            config.params.imePrezime = $scope.trazeniIgrac.imePrezime;
	        }

	        if($scope.trazeniIgrac.broj != ""){
	            config.params.broj = $scope.trazeniIgrac.broj;
	        }

	        if($scope.trazeniIgrac.timId != ""){
	            config.params.timId = $scope.trazeniIgrac.timId;
	        }
	        
		
		
		$http.get(baseUrlIg, config).then(
			function success(res){
				$scope.igraci = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	var getTimovi = function(){
		$http.get(baseUrlTimovi).then(
			function success(res){
				$scope.timovi = res.data;
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	var getPozicije = function(){
		$http.get(baseUrlPozicije).then(
			function success(res){
				$scope.pozicije = res.data;
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	getIgraci();
	getTimovi();
	getPozicije();
	
	
	$scope.dodaj = function(){
		
		$http.post(baseUrlIg, $scope.noviIgrac).then(
			function success(res){
				getIgraci();
				
				$scope.noviIgrac.imePrezime = "";
				$scope.noviIgrac.broj = "";
				$scope.noviIgrac.pozicijaId = "";
				$scope.noviIgrac.timId = "";
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	  $scope.nazad = function(){
	        if($scope.pageNum > 0) {
	            $scope.pageNum = $scope.pageNum - 1;
	            getIgraci();
	        }
	    };

	    $scope.napred = function(){
	        if($scope.pageNum < $scope.totalPages - 1){
	            $scope.pageNum = $scope.pageNum + 1;
	            getIgraci();
	        }
	    };
	
//	$scope.changeHappened = function(){
//		alert("It did!");
//	}
	
//	$scope.vece=function(){
//		$scope.trazeniIgrac.maxC = 1000;
//		
//		getIgraci();		
//	}
	
	$scope.trazi=function(){
		 $scope.pageNum = 0;
		getIgraci();
	}
	
	 $scope.izmeni = function(id){
	        $location.path('/igraci/edit/' + id);
	    }
	 
//	 $scope.dodajPrevoznika = function(){
//	        $location.path('/linije/dodajPrevoznika/');
//	    }
//	    $scope.vratiSe = function(){
//	        $location.path('/linije/');
//	    }
	 
	 $scope.obrisi = function(id){
	        $http.delete(baseUrlIg + "/" + id).then(
	            function success(data){
	            	getIgraci();
	            },
	            function error(data){
	                alert("Neuspesno brisanje!");
	            }
	        );
	    }
	
	$scope.go = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getIgraci();
	}
	
//	$scope.vece=function(){
//		$scope.trazeniIgrac.maxC = 1000;
//		
//		getIgraci();		
//	}
	
	 $scope.faul = function(id){
	    	$http.post(baseUrlIg + "/" + id + "/faul").then(
	    		function success(data){
//	    			alert("Faul je uspesno dodat.");
	    			getIgraci();
	    		},
	    		function error(data){
	    			alert("Nije uspelo dodavanje licne greske.")
	    		}
	    	)
	    }
	 
});
	 
 app.controller("editIgracCtrl", function($scope, $http, $routeParams, $location){

		    var baseUrlIg = "/api/igraci";

		    $scope.stariIgrac = null;

		    var getStariIgrac = function(){

		        $http.get(baseUrlIg + "/" + $routeParams.id)
		            .then(
		            	function success(data){
		            		$scope.stariIgrac = data.data;
		            	},
		            	function error(data){
		            		alert("Neušpesno dobavljanje igraca.");
		            	}
		            );

		    }
		    getStariIgrac();
		    
		    $scope.izmeni = function(){
		        $http.put(baseUrlIg + "/" + $scope.stariIgrac.id, $scope.stariIgrac)
		            .then(
		        		function success(data){
		        			alert("Uspešno izmenjen objekat!");
		        			$location.path("/");
		        		},
		        		function error(data){
		        			alert("Neuspešna izmena igraca.");
		        		}
		            );
		    }
	
});








