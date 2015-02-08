'use strict';

picarApp.controller('appCtrl',
    function appCtrl($scope, $mdSidenav, $location) {
        $scope.toggleSidenav = function(menuId) {
            $mdSidenav(menuId).toggle();
        };

        $scope.navitems = [
            {
                title: 'Drive',
                route: '/'
            },
            {
                title: 'Sequence',
                route: '/sequence'
            }];

        $scope.navigate = function(route) {
            $location.url(route);
        }
    }
);