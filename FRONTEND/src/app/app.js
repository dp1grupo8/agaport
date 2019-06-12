'use strict';

var app = angular.module('BlurAdmin', [
  'ngAnimate',
  'ui.bootstrap',
  'ui.sortable',
  'ui.router',
  'ngTouch',
  'toastr',
  'smart-table',
  "xeditable",
  'ui.slimscroll',
  'ngJsTree',
  'angular-progress-button-styles',

  'BlurAdmin.theme',
  'BlurAdmin.pages',
  'BlurAdmin.login',
]);

// app.run( function ($window) {
//   $window.location='auth.html';
//   console.log("hola");
// });

var globalBackendLink = 'http://200.16.7.178/backendAGAPORT';