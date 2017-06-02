var config = {
    'api_url': 'api',
    'img_url': 'img',
};



var app = angular.module('pizzas', ['ngMaterial', 'ui.router', 'ngMessages', 'toastr', 'satellizer']);

app.run(function($rootScope) {
    $rootScope.config = config;
});


app.config(['$stateProvider', '$urlRouterProvider', '$authProvider', function($stateProvider, $urlRouterProvider, $authProvider) {
    /**
     * Authorization
     */
    var skipIfLoggedIn = ['$q', '$auth', '$state', function($q, $auth, $state) {
        var deferred = $q.defer();
        if ($auth.isAuthenticated()) {
            $state.go("listPizza");
        } else {
            deferred.resolve();
        }
        return deferred.promise;
    }];

    var loginRequired = ['$q', '$location', '$auth', '$state', function($q, $location, $auth, $state) {
        var deferred = $q.defer();
        if ($auth.isAuthenticated()) {
            deferred.resolve();
        } else {
            $state.go("login");
        }
        return deferred.promise;
    }];

    $authProvider.loginUrl = config.api_url+'/auth/login';
    $authProvider.tokenName = "token";
    $authProvider.tokenPrefix = "pizzas";
    $authProvider.tokenHeader = 'authorization';
    $authProvider
        .github({
            clientId: '85527f9c1f13e386348b',
            url: config.api_url+'/auth/github'
        });
    /**
     * End Authorization
     */


    /**
     * Routing
     */
    $urlRouterProvider.otherwise('/pizzas');

    $stateProvider
    .state('mainLayout', {
        abstract: true,
        templateUrl: "../views/layouts/main.html",
        controller: 'mainController'
    })
    .state('listPizza', {
        parent: 'mainLayout',
        url: '/pizzas',
        templateUrl: '../views/pizzas/list.html',
        controller: 'listPizzaController',
        resolve: {
            loginRequired: loginRequired
        }
    })
    .state('createPizza', {
        parent: 'mainLayout',
        url: '/pizzas/add',
        templateUrl: '../views/pizzas/create.html',
        controller: 'createPizzaController',
        resolve: {
            loginRequired: loginRequired
        }
    })
    .state('viewPizza', {
        parent: 'mainLayout',
        url: '/pizzas/:id',
        templateUrl: '../views/pizzas/view.html',
        controller: 'viewPizzaController',
        resolve: {
            loginRequired: loginRequired
        }
    })
    .state('login', {
        url: '/login',
        templateUrl: '../views/user/login_form.html',
        controller: 'loginController',
        resolve: {
            skipIfLoggedIn: skipIfLoggedIn
        }
    })
    .state('logout', {
        url: '/logout',
        controller: 'logoutController'
    })
    .state('register', {
        url: '/register',
        templateUrl: '../views/user/register_form.html',
        controller: 'registerController',
        resolve: {
            skipIfLoggedIn: skipIfLoggedIn
        }
    });
    /**
     * End routing
     */
}]);

/**
 * Filters
 */
app.filter("fotoToUrl", function(){
    return function(input){;
        return config.img_url + "/" + input;
    }
});

