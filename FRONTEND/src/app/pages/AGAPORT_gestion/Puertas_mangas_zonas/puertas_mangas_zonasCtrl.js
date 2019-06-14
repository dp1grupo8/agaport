(function () {
    'use strict';
  
    angular.module('Agaport.gestion.puertas_mangas_zonas')
        .controller('PuertasMangasZonasCtrl', PuertasMangasZonasCtrl);
  
    /** @ngInject */
    function PuertasMangasZonasCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
  
      $scope.smartTablePageSize = 10;
	    $scope.puertaseleccionada=[];
      $scope.datosPuertas='';
      
      $scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
        $http({
          method:'GET',
          url: globalBackendLink + '/puertas/listar'
        }).then(function successCallback(response) {
          $scope.datosPuertas = response.data;
        },function errorCallback(response) {
          console.log('error al obtener datos de puertas en ' + globalBackendLink);
        });
      });
  
      $http({
        method:'GET',
        url: globalBackendLink + '/puertas/listar'
      }).then(function successCallback(response) {
        $scope.datosPuertas = response.data;
      },function errorCallback(response) {
        console.log('error al obtener datos de puertas en ' + globalBackendLink);
      });

      $scope.eliminarAerolinea = function(idPuerta) {
        var variable_entrega={"idPuerta":idPuerta};
        $http({
          method:'POST',
          url: globalBackendLink + '/puertas/eliminar',
          data: $.param(variable_entrega),
          headers:{
            'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
          }
        }).then(function successCallback(response) {
          console.log("exito");
        },function errorCallback(response) {
          console.log('error en obtener puertas de '+globalBackendLink);
        });
      };
  
      editableOptions.theme = 'bs3';
      editableThemes['bs3'].submitTpl = '<button type="submit" class="btn btn-primary btn-with-icon"><i class="ion-checkmark-round"></i></button>';
      editableThemes['bs3'].cancelTpl = '<button type="button" ng-click="$form.$cancel()" class="btn btn-default btn-with-icon"><i class="ion-close-round"></i></button>';
  
      $scope.open = function (page, size) {
        $uibModal.open({
          controller:'PuertasMangasZonasEliminarCtrl',
          animation: true,
          templateUrl: page,
          size: size,
          resolve: {
            puertaEliminar: function () {
              return $scope.puertaseleccionada;
            }
          }
        });
      };
      $scope.openProgressDialog = baProgressModal.open; 
      
      $scope.seleccionarPuerta=function(puerta){
        $scope.puertaseleccionada=puerta;
      };

    }

    angular.module('Agaport.gestion.puertas_mangas_zonas')
        .controller('PuertasMangasZonasNuevoCtrl', PuertasMangasZonasNuevoCtrl);
  
    /** @ngInject */
    function PuertasMangasZonasNuevoCtrl($scope, $state, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
      console.log('controlador nuevo');
      $scope.puertaSeleccionada={};

      //se carga el comboBox
      $scope.tiposSelect = [{tipo:1,nombre:'Manga'},{tipo:0,nombre:'Zona'}];
      $scope.estadosSelect=[{estado:0,nombre:'Deshabilitada'},{estado:1,nombre:'Habilitada'}];
      $scope.tipoPuertaSeleccionada={};
      $scope.estadoPuertaSeleccionada={};

      $http({
        method:'GET',
        url: globalBackendLink + '/puertas/listar'
      }).then(function successCallback(response) {
        $scope.puertaSelect = response.data;
      },function errorCallback(response) {
        console.log('error en obtener data de puerta de ' + globalBackendLink);
      });

      $scope.registrarPuertas=function(tipo,distancia,flujoPer,estado){
        var link_header='http://200.16.7.178:8080';
        var variable_entrega={"Tipo":tipo,"distanciaASalida": distancia,"flujoPersonas": flujoPer,"Estado": estado};
    
        $http({
          url: link_header + '/puertas/insertar',
          method: 'POST',
          data: $.param(variable_entrega),
          headers:{
            'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
          }
        }).then(function() {
          console.log('post puertas success');
          $state.go('agaport_gestion.puertas_mangas_zonas');
        },function(response){
          console.log('error POST');
          console.log(response);
          $state.go('agaport_gestion.puertas_mangas_zonas');
        });

      }
    };

    angular.module('Agaport.gestion.puertas_mangas_zonas')
        .controller('PuertasMangasZonasModificarCtrl', PuertasMangasZonasModificarCtrl);

    /** @ngInject */
    function PuertasMangasZonasModificarCtrl($scope,$state, $stateParams, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
      console.log('controlador modificar');
      $scope.puertaSeleccionadoModificar=angular.copy($stateParams);
      console.log($scope.puertaSeleccionadoModificar);

      $scope.puertaSeleccionada={};
      $scope.tipoPuertaSeleccionada={};
      $scope.estadoPuertaSeleccionada={};
      //se carga el comboBox
      
      $scope.tiposSelect = [{tipo:1,nombre:'Manga'},{tipo:0,nombre:'Zona'}];
      $scope.estadosSelect=[{estado:0,nombre:'Deshabilitada'},{estado:1,nombre:'Habilitada'}];
      $scope.tipoPuertaSeleccionada.selected=$scope.tiposSelect.find(tipo => tipo.tipo==$scope.puertaSeleccionadoModificar.tipo);
      console.log($scope.puertaSeleccionadoModificar.tipo);
      $scope.estadoPuertaSeleccionada.selected=$scope.estadosSelect.find(estado => estado.estado==$scope.puertaSeleccionadoModificar.estado);

      //se carga el comboBox
      $http({
        method:'GET',
        url: globalBackendLink + '/puertas/listar'
      }).then(function successCallback(response) {
        $scope.puertaSelect = response.data;
        $scope.puertaSeleccionada.selected = $scope.puertaSelect.find(puerta => puerta.tipo==$scope.puertaSeleccionadoModificar.tipo);
      },function errorCallback(response) {
        console.log('error en obtener data de puertas de ' + globalBackendLink);
      });

      $scope.modificarPuerta= function (idPuerta,tipo,distanciaASalida,flujoPersonas,estado){

        var variable_entrega={"idPuerta":idPuerta,"Tipo": tipo,"distanciaASalida": distanciaASalida,"flujoPersonas": flujoPersonas,"Estado":estado};

        $http({
          url: globalBackendLink + '/puertas/modificar',
          method: 'POST',
          data: $.param(variable_entrega),
          headers:{
            'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
          }
        }).success(function(data, status, headers, config) {
          console.log('post puertas success');

          $state.go('agaport_gestion.puertas');
        }).error(function(data, status, headers, config){
          $state.go('agaport_gestion.puertas_mangas_zonas');
        });
      }

  
    }

    angular.module('Agaport.gestion.puertas_mangas_zonas')
        .controller('PuertasMangasZonasEliminarCtrl', PuertasMangasZonasEliminarCtrl);
    
    /** @ngInject */
    function PuertasMangasZonasEliminarCtrl($scope, puertaEliminar, $state, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
      console.log('controlador eliminar');
      
      $scope.confirmarEliminado = function () {
        console.log(puertaEliminar);

        $scope.confirmarEliminado = function(){
          var variable_entrega={"idPuerta":puertaEliminar.idPuerta};
  
          $http({
            url: globalBackendLink + '/puertas/eliminar',
            method: 'POST',
            data: $.param(variable_entrega),
            headers:{
              'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
            }
          }).success(function(data, status, headers, config) {
            console.log('post puertas success');
    
            $state.go('agaport_gestion.puertas_mangas_zonas');
          }).error(function(data, status, headers, config){
            console.log("data");
            console.log(data);
            console.log("status");
            console.log(status);
            console.log($uibModal);
            $state.go('agaport_gestion.puertas_mangas_zonas');
          });
        }
      }
    }

  })();
  

