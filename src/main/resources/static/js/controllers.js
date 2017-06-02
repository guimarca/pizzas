app.controller('mainController', ['$scope', '$http', '$log', '$mdSidenav', function($scope, $http, $log, $mdSidenav) {
    $scope.toggleLeft = function() {
        $mdSidenav('left').toggle();
    }
}]);


app.controller('loginController', ['$scope', '$auth', '$log', '$state', 'toastr', '$http', function($scope, $auth, $log, $state, toastr, $http) {
    $scope.usuario = {};

    $scope.login = function(usuario) {
        $auth.login(usuario).then(function(data) {
            toastr.success('Login correcto');
            $auth.setToken(data.headers()['authorization']);

            $state.go("listPizza");
        })
        .catch(function(error) {
            toastr.error('Login incorrecto');
            $log.log(error);
        });
    }

    $scope.authenticate = function(provider) {
        $auth.authenticate(provider)
            .then(function(data) {
                toastr.success('Login correcto con ' + provider + '!');
                $auth.setToken(data.headers()['authorization']);
                //console.log($auth.getToken());
                $state.go("listPizza");
            })
            .catch(function(error) {
                if (error.message) {
                    // Satellizer promise reject error.
                    $log.log(error.message);
                } else if (error.data) {
                    // HTTP response error from server
                    $log.log(error.data.message, error.status);
                } else {
                    $log.log(error);
                }
            });
    };
}]);


app.controller('logoutController', ['$scope', '$auth', '$log', '$state', function($scope, $auth, $log, $state) {
    $auth.logout().then(function() {
        $state.go("login");
    })
}]);


app.controller('registerController', ['$scope', '$http', '$log', '$state', 'toastr', '$auth', function($scope, $http, $log, $state, toastr, $auth) {
    $scope.usuario = {};
    $scope.server_errors = {};

    $scope.addUsuario = function(usuario) {
        $http.post(config.api_url + "/usuarios", usuario)
            .then(function successCallback(response) {
                toastr.success("Registrado correctamente");

                $auth.login({email:usuario.email, password:usuario.password}).then(function(data) {
                    toastr.success('Login correcto');
                    $auth.setToken(data.headers()['authorization']);
                    $state.go("listPizza");
                })
            }, function errorCallback(response) {
                toastr.error("Error con tu registro");
                $log.log(response);
            });
    }
}]);


app.controller('listPizzaController', ['$scope', '$http', '$log', '$auth', function($scope, $http, $log, $auth) {
    $scope.pizzas = [];
    $http.get(config.api_url+"/pizzas").then(function(response) {
        $scope.pizzas = response.data;
    });
}]);


app.controller('viewPizzaController', ['$scope', '$http', '$log', '$stateParams', '$auth', 'toastr', function($scope, $http, $log, $stateParams, $auth, toastr) {
    var id = $scope.id = $stateParams.id;
    $scope.comentario = {};

    $http.get(config.api_url+"/pizzas/"+id).then(function(response) {
        $scope.pizza = response.data;
    });

    $scope.addComentario = function(comentario) {
        comentario.usuario = {id: $auth.getPayload().jti};
        comentario.pizza = {id: id};
        comentario.fecha = new Date().getTime();

        $http.post(config.api_url + "/comentarios", comentario)
            .then(function successCallback(response) {
                toastr.success("Comentario guardado");
                $scope.pizza.comentarios.splice(0, 0, comentario);
            }, function errorCallback(response) {
                toastr.error("Error guardando comentario");
            });
    }
}]);


app.controller('createPizzaController', ['$scope', '$http', '$log', 'toastr', '$state', function($scope, $http, $log, toastr, $state) {
    $scope.pizza = {};
    $scope.pizza.ingredientes = [];

    $http.get(config.api_url+"/ingredientes").then(function(response) {
        $scope.ingredientes = response.data;
    });


    $scope.setFile = function(element) {
        $scope.currentFile = element.files[0];
        $scope.pizza.foto_extension = element.files[0].name.split(".").pop();
        var reader = new FileReader();

        reader.onload = function(event) {
            $scope.image_source = event.target.result;
            $scope.$apply();
        }
        reader.readAsDataURL(element.files[0]);
    }



    $scope.savePizza = function(pizza) {
        var ingredientes = [];
        for (var i=0; i<pizza.ingredientes.length; i++) {
            ingredientes[i] = {id: pizza.ingredientes[i]};
        }

        var data = new FormData();
        data.append("nombre", pizza.nombre);
        data.append("foto_extension", pizza.foto_extension);
        data.append("ingredientes", JSON.stringify(ingredientes));
        data.append('foto_file', $scope.currentFile);


        $http.post(config.api_url + "/pizzas", data, {
            headers: {'Content-Type': undefined },
        })
        .then(function successCallback(response) {
            toastr.success("Pizza creada");
            $state.go("listPizza");
        }, function errorCallback(response) {
            toastr.error("Error creando la pizza");
            $log.log(response);
        });
    }
}]);
