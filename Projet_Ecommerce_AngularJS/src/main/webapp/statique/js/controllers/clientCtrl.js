monApp

		.controller(
				'clientAllCatCtrl',
				function($rootScope, $scope, clientFactory, $location) {

					// Initialiser indice ng-show
					$rootScope.indiceConnexion = false;
					$rootScope.indiceClientExist = false;

					// Obtenir toutes les catégories
					clientFactory.getAllCat(function(callback) {
						$scope.allCat = callback;
					})

					// Obtenir les produits de la catégorie sélectionnée
					$scope.doGetProdByCat = function(cat) {
						clientFactory.getProdByCat(cat.id_categorie, function(
								callback) {
							$rootScope.prodByCat = callback;
						})
						$location.path('client/prodByCat')
					}
					
					// Obtenir tous les produits
					$scope.doGetAllProd = function() {
						$location.path('client/allProd')
					}

					// Aller au panier
					$rootScope.panier = {
						'id_panier' : undefined,
						'prixTotal' : undefined,
						'clientP' : undefined,
						'listeLC' : undefined
					}
					$scope.goPanier = function() {
						clientFactory.getPanier(function(callback) {
							$rootScope.panier = callback;
							$location.path('client/panier');
						})
					}

				})

		.controller('clientAllProdCtrl', function($scope, clientFactory) {

			clientFactory.getAllProd(function(callback) {
				$scope.allProd = callback;
			})
		})

		.controller(
				'clientProdByCatCtrl',
				function($rootScope, $scope, clientFactory, $location) {

					// Obtenir les produits d'une cétgorie
					$scope.id = undefined;
					$scope.doGetProdByCat = function() {
						clientFactory.getProdByCat($scope.id,
								function(callback) {
									$scope.prodByCat = callback;
								})
					}

					// Ajouter au panier
					$scope.doAddPanier = function(prod) {
						clientFactory.addPanier(prod.nom, function(callback) {
							$rootScope.panier = callback;
						})

						$location.path('client/allCat');
					}

				})

		.controller(
				'clientPanierCtrl',
				function($rootScope, $scope, clientFactory) {

					clientFactory.getPanier(function(callback) {
						$scope.panier = callback;
					})

					// Modifier indice ng-show pour modifier le bouton qui
					// apparait
					$rootScope.indiceConnexion = true;

					// Supprimer du panier
					$scope.doDeletePanier = function(prod) {
						clientFactory.deletePanier(prod.nom,
								function(callback) {
									$scope.panier = callback;
								})
						clientFactory.getPanier(function(callback) {
							$scope.panier = callback;
						})
					}
				})

		.controller(
				'clientIdentCtrl',
				function($rootScope, $scope, clientFactory, $location) {

					$scope.nom = undefined;
					$scope.password = undefined;

					// Si connexion depuis le panier
					$scope.doClientExist = function(nom, password) {

						console.log("--------------------Nom : " + nom
								+ "---------------------------Password : "
								+ password);

						clientFactory.clientExist(nom, password, function(
								callback) {
							$scope.client = callback;
							console.log($scope.client);

							if ($scope.client.nom == undefined) {

							} else {
								$rootScope.client = $scope.client;
								$location.path('client/valid');
							}
						})

					}

					// Si connexion depuis une autre page
					$scope.doClientExist2 = function(nom, password) {

						console.log("--------------------Nom : " + nom
								+ "---------------------------Password : "
								+ password);

						clientFactory.clientExist(nom, password, function(
								callback) {
							$scope.client = callback;
							console.log($scope.client);
							
							if ($scope.client.nom== undefined) {
								
							} else {

							$rootScope.client = $scope.client;
							$location.path('client/oldComm');

							}
						})

					}

				})

		.controller('clientValidCommCtrl',
				function($rootScope, $scope, clientFactory, $location) {

					clientFactory.getPanier(function(callback) {
						$scope.panier = callback;
					})

					$scope.nom = $rootScope.client.nom;
					$scope.doFinaliser = function(nom) {
						clientFactory.finaliser(nom, function(callback) {
							$location.path('client/allCat');
						})
					}

				})

		.controller(
				'clientaddClientCtrl',
				function($rootScope, $scope, clientFactory, $location) {

					$scope.clientForm = {
						nom : undefined,
						adresse : undefined,
						mail : undefined,
						password : undefined,
						tel : undefined,
					}

					$scope.doAddClient = function() {

						clientFactory
								.addClient(
										$scope.clientForm,
										function(callback) {

											$scope.client = callback;

											if ($scope.client.nom == undefined) {
												$rootScope.indiceClientExist = true;
												$scope.indiceClientExist = $rootScope.indiceClientExist;
												$location
														.path('client/addClient');
											} else {
												$rootScope.indiceClientExist = false;
												$scope.indiceClientExist = $rootScope.indiceClientExist;
												$location.path('client/ident');
											}

										})
					}
				})

		.controller(
				'clientOldCommandesCtrl',
				function($rootScope, $scope, clientFactory, $location) {
					$scope.client = $rootScope.client;

					clientFactory.getCommandes($scope.client,
							function(callback) {
								$scope.commandes = callback;
								console.log($scope.commandes);
							});
					
					console.log($scope.commandes);
				
					
					$scope.doGetOldPanier = function(commande) {
						clientFactory.getOldPanier(commande, function(callback) {
							$rootScope.oldPanier=callback;
							$rootScope.commande= $scope.commande;
						})
						$location.path('client/oldPanier');
					}

				})
				
				
		.controller(
				'clientOldPanierCtrl',
				function($rootScope, $scope, clientFactory, $location) {

					// Obtenir les produits d'une cétgorie
					//$scope.commande = undefined;
					$scope.allLCOldPanier = $rootScope.oldPanier;


				})
				
				