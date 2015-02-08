'use strict';

picarApp.controller('sequenceCtrl',
    function sequenceCtrl($scope, $http) {
        $scope.items = [];

        $scope.addItem = function() {
            var item = {
                state: {
                    turn: "STRAIGHT",
                    throttle: "STOP"
                },
                duration: 100
            };

            $scope.items.push(item);
        };

        $scope.removeItem = function() {
            $scope.items.pop();
        };

        $scope.sendItems = function() {
            $http.post('/api/sequence', $scope.items);
        };
    }
);