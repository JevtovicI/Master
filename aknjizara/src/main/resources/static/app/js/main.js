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
		.when('/knjige', {
			templateUrl : '/app/html/knjige.html'
		}).when('/knjige/edit/:id',{
	        templateUrl: '/app/html/edit-knjiga.html'
	    }).when('/knjige/dodajIzdavaca/',{
	        templateUrl: '/app/html/dodaj-izdavaca.html'
	    }).when('/izdavaci',{
	        templateUrl: '/app/html/izdavaci.html'
	    })
		.otherwise({
			redirectTo: '/'
		});
}]);


app.controller("knjigeCtrl", function($scope, $http, $location){
	
	var baseUrlK = "/api/knjige";
	var baseUrlIzdavac = "/api/izdavaci";
	
	$scope.pageNum = 0;
    $scope.totalPages = 0;
	
	$scope.knjige = [];
	$scope.izdavaci = [];
	
	
	
	$scope.novaKnjiga = {};
	$scope.novaKnjiga.naziv = "";
	$scope.novaKnjiga.izdanje = "";
	$scope.novaKnjiga.pisac = "";
	$scope.novaKnjiga.isbn = "";
	$scope.novaKnjiga.brojGlasova = "";
	$scope.novaKnjiga.izdavacId = "";
	
	$scope.noviIzdavac = {};
	$scope.noviIzdavac.naziv = "";
	$scope.noviIzdavac.adresa = "";
	$scope.noviIzdavac.telefon = "";
	
	
	$scope.trazenaKnjiga={};
	$scope.trazenaKnjiga.naziv ="";
	$scope.trazenaKnjiga.pisac ="";
	$scope.trazenaKnjiga.minG ="";	
	
	
	var getKnjige = function(){
		
		var config = { params:{}};
		
		config.params.pageNum = $scope.pageNum;
		
		  if($scope.trazenaKnjiga.naziv != ""){
	            config.params.naziv = $scope.trazenaKnjiga.naziv;
	        }

	        if($scope.trazenaKnjiga.pisac != ""){
	            config.params.pisac = $scope.trazenaKnjiga.pisac;
	        }

	        if($scope.trazenaKnjiga.minG != ""){
	            config.params.minG = $scope.trazenaKnjiga.minG;
	        }
	        
		
		
		$http.get(baseUrlK, config).then(
			function success(res){
				$scope.knjige = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	var getIzdavaci = function(){
		$http.get(baseUrlIzdavac).then(
			function success(res){
				$scope.izdavaci = res.data;
				console.log($scope.izdavaci);
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	getKnjige();
	getIzdavaci();
	
	  $scope.edit = function(knjigaId) {
	        console.log(knjigaId);
	        $scope.novaKnjiga.id = knjigaId;
	        $http.put(baseUrlK + "/" + knjigaId, $scope.novaKnjiga).then(
	                function success(res){
	                	$scope.novaKnjiga.id = "";
	                	$scope.novaKnjiga.naziv = "";
	                	$scope.novaKnjiga.izdanje = "";
	                	$scope.novaKnjiga.pisac = "";
	                	$scope.novaKnjiga.isbn = "";
	                	$scope.novaKnjiga.brojGlasova = "";
	                	$scope.novaKnjiga.izdavacId = "";
	                    $scope.showEditButton = false;
	                    getKnjige();
	                },
	                function error(res){
	                    alert("Something went wrong!");
	                }   
	        );
	    }
	
	$scope.dodaj = function(){
		
		$http.post(baseUrlK, $scope.novaKnjiga).then(
			function success(res){
				getKnjige();
				
				$scope.novaKnjiga.naziv = "";
            	$scope.novaKnjiga.izdanje = "";
            	$scope.novaKnjiga.pisac = "";
            	$scope.novaKnjiga.isbn = "";
            	$scope.novaKnjiga.brojGlasova = "";
            	$scope.novaKnjiga.izdavacId = "";
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	  $scope.nazad = function(){
	        if($scope.pageNum > 0) {
	            $scope.pageNum = $scope.pageNum - 1;
	            getKnjige();
	        }
	    };

	    $scope.napred = function(){
	        if($scope.pageNum < $scope.totalPages - 1){
	            $scope.pageNum = $scope.pageNum + 1;
	            getKnjige();
	        }
	    };
	
	$scope.changeHappened = function(){
		alert("It did!");
	}
	
	$scope.vece=function(){
		$scope.trazenaKnjiga.minG = 10;
		
		getKnjige();		
	}
	
	$scope.trazi=function(){
		 $scope.pageNum = 0;
		 getKnjige();
	}
	
	 $scope.izmeni = function(id){
	        $location.path('/knjige/edit/' + id);
	    }
	 
	 $scope.dodajIzdavaca = function(){
	        $location.path('/knjige/dodajIzdavaca/');
	    }
	    $scope.vratiSe = function(){
	        $location.path('/knjige/');
	    }
	 
	 $scope.obrisi = function(id){
	        $http.delete(baseUrlK + "/" + id).then(
	            function success(data){
	            	getKnjige();
	            },
	            function error(data){
	                alert("Neuspesno brisanje!");
	            }
	        );
	    }
	
	$scope.go = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getKnjige();
	}
	
	$scope.vece=function(){
		$scope.trazenaKnjiga.minG = 10;
		
		getKnjige();		
	}
	
	 $scope.glasaj = function(id){
	    	$http.post(baseUrlK + "/" + id + "/glasanje").then(
	    		function success(data){
	    			alert("Glasanje je uspesno obavljeno.");
	    			getKnjige();
	    		},
	    		function error(data){
	    			alert("Nije uspelo glasanje.")
	    		}
	    	)
	    }
	 
});
	 
 app.controller("editKnjigaCtrl", function($scope, $http, $routeParams, $location){

		    var baseUrlK = "/api/knjige";
		    var baseUrlIzdavac = "/api/izdavaci";

		    $scope.staraKnjiga = null;
		    
		    var getIzdavaci = function(){
				$http.get(baseUrlIzdavac).then(
					function success(res){
						$scope.izdavaci = res.data;
						console.log($scope.izdavaci);
					},
					function error(res){
						alert("Something went wrong!");
					}
				);
			}

		    var getStaraKnjiga = function(){

		        $http.get(baseUrlK + "/" + $routeParams.id)
		            .then(
		            	function success(data){
		            		$scope.staraKnjiga = data.data;
		            	},
		            	function error(data){
		            		alert("Neušpesno dobavljanje knjiga.");
		            	}
		            );

		    }
		    getStaraKnjiga();
		    getIzdavaci();
		    
		    $scope.izmeni = function(){
		        $http.put(baseUrlK + "/" + $scope.staraKnjiga.id, $scope.staraKnjiga)
		            .then(
		        		function success(data){
		        			alert("Uspešno izmenjen objekat!");
		        			$location.path("/knjige");
		        		},
		        		function error(data){
		        			alert("Neuspešna izmena knjige.");
		        		}
		            );
		    }
	
});

 app.controller("addIzdavacCtrl", function($scope, $http, $routeParams, $location){

	    var baseUrlIzdavac = "/api/izdavaci";


//	    var getStaraLinija = function(){
//
//	        $http.get(baseUrlIzdavac + "/" + $routeParams.id)
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
			
			$http.post(baseUrlIzdavac, $scope.noviIzdavac).then(
				function success(res){
					getIzdavaci();
					
					$scope.noviIzdavac.naziv = "";
					$scope.noviIzdavac.adresa = "";
					$scope.noviIzdavac.telefon = "";
				},
				function error(res){
					alert("Something went wrong!");
				}
			);
		}
	    
//	    getPrevoznici();

});



