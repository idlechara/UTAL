var geolocate;

$(document)
		.ready(
				function() {

					var gg = new OpenLayers.Projection("EPSG:4326");
					var sm = new OpenLayers.Projection("EPSG:900913");
					
					// Creación del mapa y encuadre inicial
					var WGS84 = new OpenLayers.Projection('EPSG:4326');
					var MERCATOR = new OpenLayers.Projection('EPSG:900913');
					var utal = new OpenLayers.Bounds(-71.229806, -35.001777,
							-71.229768, -35.001742).transform(WGS84, MERCATOR);
			
				    geolocate = new OpenLayers.Control.Geolocate({
				        id: 'locate-control',
				        geolocationOptions: {
				            enableHighAccuracy: true,
				            maximumAge: 0,
				            timeout: 7000
				        }
				    });
					
					var mapa = new OpenLayers.Map({
						div : "mapa",
						numZoomLevels: 18,        
						projection: sm,
				        controls: [
				            //new OpenLayers.Control.Attribution(),					
				            new OpenLayers.Control.Navigation(),
							new OpenLayers.Control.PanZoomBar(),
				            new OpenLayers.Control.TouchNavigation({
				                dragPanOptions: {
				                    enableKinetic: true
				                }
				            }),
				            geolocate
				        ],
				        zoom: 1
					});
					mapa.addLayer(new OpenLayers.Layer.OSM("OpenStreetMap", null, {
		                transitionEffect: 'resize'
		            }));
					mapa.zoomToExtent(utal);

					// Creación de una capa vectorial con 1 registro puntual
					var oficinas = new OpenLayers.Layer.Vector("Oficinas");
					mapa.addLayer(oficinas);
					var capaUtal = new OpenLayers.Layer.Vector("CapaUtal", {
						strategies : [ new OpenLayers.Strategy.Fixed() ],
						protocol : new OpenLayers.Protocol.HTTP({
							url : "edificios.osm", // <-- relative or absolute
													// URL to your .osm file
							format : new OpenLayers.Format.OSM()
						}),
						projection : new OpenLayers.Projection("EPSG:4326"),
						transitionEffect : 'resize'
					});
					mapa.addLayer(capaUtal);

					var controlSeleccion = new OpenLayers.Control.SelectFeature(
							capaUtal, {
								onSelect : onFeatureSelect,
								onUnselect : onFeatureUnselect
							});
					mapa.addControl(controlSeleccion);
					controlSeleccion.activate();

					var selectedFeature, popup;

					function onPopupClose(evt) {
						selectControl.unselect(selectedFeature);
					}
					function onFeatureSelect(feature) {
						selectedFeature = feature;
						popup = new OpenLayers.Popup.FramedCloud(
								"chicken",
								feature.geometry.getBounds().getCenterLonLat(),
								null,
								"<div style='font-size:.8em'>Feature: "
										+ feature.id
										+ "<br>Area: "
										+ feature.geometry.getArea()
										+ "<br>Center coord: "
										+ feature.geometry.getBounds()
												.getCenterLonLat()
										+ "<br> Aca le toca al garrao aplicarse, con la base<br>de datos... aca le puedo entregar como <br>identificador el centro de la figura en<br>cuestion, para que me entrege la info.</div>",
								null, true, onPopupClose);
						feature.popup = popup;
						mapa.addPopup(popup);
					}
					function onFeatureUnselect(feature) {
						mapa.removePopup(feature.popup);
						feature.popup.destroy();
						feature.popup = null;
					}
					
				    var vector = new OpenLayers.Layer.Vector("Vector Layer", {});
				    mapa.addLayer(vector);
				    var style = {
				            fillOpacity: 0.1,
				            fillColor: '#000',
				            strokeColor: '#f00',
				            strokeOpacity: 0.6
				        };
				    geolocate.events.register("locationupdated", this, function(e) {
				        vector.removeAllFeatures();
				        vector.addFeatures([
				            new OpenLayers.Feature.Vector(
				                e.point,
				                {},
				                {
				                    graphicName: 'cross',
				                    strokeColor: '#f00',
				                    strokeWidth: 2,
				                    fillOpacity: 0,
				                    pointRadius: 10
				                }
				            ),
				            new OpenLayers.Feature.Vector(
				                OpenLayers.Geometry.Polygon.createRegularPolygon(
				                    new OpenLayers.Geometry.Point(e.point.x, e.point.y),
				                    e.position.coords.accuracy / 2,
				                    50,
				                    0
				                ),
				                {},
				                style
				            )
				        ]);
				        mapa.zoomToExtent(vector.getDataExtent());
				    });

				});

function locate(){
	//var control = mapa.getControlsBy("id", "locate-control")[0];
    if (geolocate.active) {
    	geolocate.getCurrentLocation();
    } else {
    	geolocate.activate();
    }
}
