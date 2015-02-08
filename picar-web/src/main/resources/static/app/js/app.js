'use strict';

var picarApp = angular.module('picarApp', ['ngMaterial', 'ngRoute'])
    .config(function ($routeProvider, $locationProvider) {
        $routeProvider
            .when('/',
            {
                templateUrl:'/app/templates/drive.html',
                controller:'driveCtrl'
            })
            .when('/sequence',
            {
                templateUrl:'/app/templates/sequence.html',
                controller:'sequenceCtrl'
            })
            .otherwise({ redirectTo: '/' });

        $locationProvider.html5Mode({ enabled: true, requireBase: false });
    });

