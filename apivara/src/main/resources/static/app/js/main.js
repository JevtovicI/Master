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
		.when('/linije', {
			templateUrl : '/app/html/linije.html'
		}).when('/linije/edit/:id',{
	        templateUrl: '/app/html/edit-linija.html'
	    }).when('/linije/dodajPrevoznika/',{
	        templateUrl: '/app/html/dodaj-prevoznika.html'
	    }).when('/prevoznici',{
	        templateUrl: '/app/html/prevoznici.html'
	    })
		.otherwise({
			redirectTo: '/'
		});
}]);


app.controller("linijeCtrl", function($scope, $http, $location){
	
	var baseUrlLi = "/api/linije";
	var baseUrlPrevoznik = "/api/prevoznici";
	
	$scope.pageNum = 0;
    $scope.totalPages = 0;
	
	$scope.linije = [];
	$scope.prevoznici = [];
	
	
	
	$scope.novaLinija = {};
	$scope.novaLinija.brojMesta = "";
	$scope.novaLinija.cenaKarte = "";
	$scope.novaLinija.vremePolaska = "";
	$scope.novaLinija.destinacija = "";
	$scope.novaLinija.prevoznikId = "";
	
	$scope.noviPrevoznik = {};
	$scope.noviPrevoznik.naziv = "";
	$scope.noviPrevoznik.adresa = "";
	$scope.noviPrevoznik.pib = "";
	
	
	$scope.trazenaLinija={};
	$scope.trazenaLinija.destinacija ="";
	$scope.trazenaLinija.prevoznikId ="";
	$scope.trazenaLinija.maxC ="";	
	
	
	var getLinije = function(){
		
		var config = { params:{}};
		
		config.params.pageNum = $scope.pageNum;
		
		  if($scope.trazenaLinija.destinacija != ""){
	            config.params.destinacija = $scope.trazenaLinija.destinacija;
	        }

	        if($scope.trazenaLinija.prevoznikId != ""){
	            config.params.prevoznikId = $scope.trazenaLinija.prevoznikId;
	        }

	        if($scope.trazenaLinija.maxC != ""){
	            config.params.maxC = $scope.trazenaLinija.maxC;
	        }
	        
		
		
		$http.get(baseUrlLi, config).then(
			function success(res){
				$scope.linije = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	var getPrevoznici = function(){
		$http.get(baseUrlPrevoznik).then(
			function success(res){
				$scope.prevoznici = res.data;
				console.log($scope.prevoznici);
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	getLinije();
	getPrevoznici();
	
	  $scope.edit = function(linijaId) {
	        console.log(linijaId);
	        $scope.novaLinija.id = linijaId;
	        $http.put(baseUrlLi + "/" + linijaId, $scope.novaLinija).then(
	                function success(res){
	                	$scope.novaLinija.id = "";
	                    $scope.novaLinija.brojMesta = "";
	                	$scope.novaLinija.cenaKarte = "";
	                	$scope.novaLinija.vremePolaska = "";
	                	$scope.novaLinija.destinacija = "";
	                	$scope.novaLinija.prevoznikId = "";
	                    $scope.showEditButton = false;
	                    getLinije();
	                },
	                function error(res){
	                    alert("Something went wrong!");
	                }   
	        );
	    }
	
	$scope.dodaj = function(){
		
		$http.post(baseUrlLi, $scope.novaLinija).then(
			function success(res){
				getLinije();
				
				$scope.novaLinija.brojMesta = "";
				$scope.novaLinija.cenaKarte = "";
				$scope.novaLinija.vremePolaska = "";
				$scope.novaLinija.destinacija = "";
				$scope.novaLinija.prevoznikId = "";
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	  $scope.nazad = function(){
	        if($scope.pageNum > 0) {
	            $scope.pageNum = $scope.pageNum - 1;
	            getLinije();
	        }
	    };

	    $scope.napred = function(){
	        if($scope.pageNum < $scope.totalPages - 1){
	            $scope.pageNum = $scope.pageNum + 1;
	            getLinije();
	        }
	    };
	
	$scope.changeHappened = function(){
		alert("It did!");
	}
	
	$scope.vece=function(){
		$scope.trazenaLinija.maxC = 1000;
		
		getLinije();		
	}
	
	$scope.trazi=function(){
		 $scope.pageNum = 0;
		getLinije();
	}
	
	 $scope.izmeni = function(id){
	        $location.path('/linije/edit/' + id);
	    }
	 
	 $scope.dodajPrevoznika = function(){
	        $location.path('/linije/dodajPrevoznika/');
	    }
	    $scope.vratiSe = function(){
	        $location.path('/linije/');
	    }
	 
	 $scope.obrisi = function(id){
	        $http.delete(baseUrlLi + "/" + id).then(
	            function success(data){
	            	getLinije();
	            },
	            function error(data){
	                alert("Neuspesno brisanje!");
	            }
	        );
	    }
	
	$scope.go = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getLinije();
	}
	
	$scope.vece=function(){
		$scope.trazenaLinija.maxC = 1000;
		
		getLinije();		
	}
	
	 $scope.kupi = function(id){
	    	$http.post(baseUrlLi + "/" + id + "/kupovina").then(
	    		function success(data){
	    			alert("Kupovina je uspesno obavljena.");
	    			getLinije();
	    		},
	    		function error(data){
	    			alert("Nije uspela kupovina karte.")
	    		}
	    	)
	    }
	 
});
	 
 app.controller("editLinijaCtrl", function($scope, $http, $routeParams, $location){

		    var baseUrlLi = "/api/linije";

		    $scope.staraLinija = null;

		    var getStaraLinija = function(){

		        $http.get(baseUrlLi + "/" + $routeParams.id)
		            .then(
		            	function success(data){
		            		$scope.staraLinija = data.data;
		            	},
		            	function error(data){
		            		alert("Neušpesno dobavljanje linije.");
		            	}
		            );

		    }
		    getStaraLinija();
		    
		    $scope.izmeni = function(){
		        $http.put(baseUrlLi + "/" + $scope.staraLinija.id, $scope.staraLinija)
		            .then(
		        		function success(data){
		        			alert("Uspešno izmenjen objekat!");
		        			$location.path("/");
		        		},
		        		function error(data){
		        			alert("Neuspešna izmena linije.");
		        		}
		            );
		    }
	
});

 app.controller("addPrevoznikCtrl", function($scope, $http, $routeParams, $location){

	    var baseUrlPrevoznik = "/api/prevoznici";


//	    var getStaraLinija = function(){
//
//	        $http.get(baseUrlPrevoznik + "/" + $routeParams.id)
//	            .then(
//	            	function success(data){
//	            		$scope.staraLinija = data.data;
//	            	},
//	            	function error(data){
//	            		alert("Neušpesno dobavljanje linije.");
//	            	}
//	            );
//
//	    }
//	    getStaraLinija();
	    
	    $scope.dodaj = function(){
			
			$http.post(baseUrlPrevoznik, $scope.noviPrevoznik).then(
				function success(res){
					getPrevoznici();
					
					$scope.noviPrevoznik.naziv = "";
					$scope.noviPrevoznik.adresa = "";
					$scope.noviPrevoznik.pib = "";
				},
				function error(res){
					alert("Something went wrong!");
				}
			);
		}
	    
//	    getPrevoznici();

});



