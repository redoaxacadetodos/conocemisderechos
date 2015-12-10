/**
 * Dark theme for Highcharts JS
 * @author Torstein Honsi
 */

// Load the fonts
Highcharts.createElement('link', {
   href: '//fonts.googleapis.com/css?family=Unica+One',
   rel: 'stylesheet',
   type: 'text/css'
}, null, document.getElementsByTagName('head')[0]);

Highcharts.theme = {
   colors: ["#27AE60", "#34495E", "#f45b5b", "#40A52B", "#aaeeee", "#ff0066", "#eeaaee",
      "#55BF3B", "#DF5353", "#1345a3", "#aaeeee"],
   chart: {
      backgroundColor: {
         linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
         stops: [
            [0, '#c49797'],/*color de fondo*/
            [1, '#621919']
         ]
      },
      style: {
         fontFamily: "'Helvetica Neue', 'Futura', sans-serif"
      },
      plotBorderColor: '#ff0000'
   },
   title: {
      style: {
         color: '#2C3E50',
         fontSize: '18px'
      }
   },
   subtitle: {
      style: {
         color: '#7F8C8D',
         textTransform: 'uppercase'
      }
   },
   xAxis: {
      gridLineColor: '#ff0000',
      labels: {
         style: {
            color: '#34495E'
         }
      },
      lineColor: '#fff',
      minorGridLineColor: '#e6e6e6',
      tickColor: '#c9c9c9',
      title: {
         style: {
            color: '#34495E'

         }
      }
   },
   yAxis: {
      gridLineColor: '#7F8C8D',
      labels: {
         style: {
            color: '#E0E0E3'
         }
      },
      lineColor: '#707073',
      minorGridLineColor: '#505053',
      tickColor: '#fff',
      tickWidth: 1,
      title: {
         style: {
            color: '#245EBC'
         }
      }
   },
   tooltip: {
      backgroundColor: '#34495E', //etiquetas rollover
      style: {
         color: '#fff'
      }
   },
   plotOptions: {
      series: {
         dataLabels: {
            enabled: true,
            color: '#b1b1b1',
            style: { fontFamily: '\'Lato\', sans-serif', lineHeight: '18px', fontSize: '18px', textShadow: "false",}
         },
         marker: {
            lineColor: '#f4f4f4'
         }
      },
      boxplot: {
         fillColor: '#245EBC'
      },
      candlestick: {
         lineColor: 'white'
      },
      errorbar: {
         color: 'white'
      }
   },
   legend: { //descripcion
      itemStyle: {
         color: '#95A5A6'
      },
      itemHoverStyle: {
         color: '#7F8C8D'
      },
      itemHiddenStyle: {
         color: '#C0392B'
      }
   },
   credits: { //.com
      style: {
         color: '#F4f4f4'
      }
   },
   labels: {
      style: {
         color: '#95A5A6'
      }
   },

   drilldown: {
      activeAxisLabelStyle: {
         color: '#9508A5'
      },
      activeDataLabelStyle: {
         color: '#F0F0F3'
      }
   },

   navigation: { //menu de impresión
      buttonOptions: {
         symbolStroke: '#7F8C8D', //3 rayas
         theme: {
            fill: '#ECF0F1' //relleno
         }
      }
   },

   // scroll charts
   rangeSelector: {
      buttonTheme: {
         fill: '#9508A5',
         stroke: '#000000',
         style: {
            color: '#CCC'
         },
         states: {
            hover: {
               fill: '#7F8C8D',
               stroke: '#000000',
               style: {
                  color: 'white'
               }
            },
            select: {
               fill: '#000003',
               stroke: '#000000',
               style: {
                  color: 'white'
               }
            }
         }
      },
      inputBoxBorderColor: '#9508A5',
      inputStyle: {
         backgroundColor: '#333',
         color: 'silver'
      },
      labelStyle: {
         color: '#9508A5'
      }
   },

   navigator: {
      handles: {
         backgroundColor: '#666',
         borderColor: '#AAA'
      },
      outlineColor: '#CCC',
      maskFill: 'rgba(255,255,255,0.1)',
      series: {
         color: '#7F8C8D',
         lineColor: '#C09B07 !important'
      },
      xAxis: {
         gridLineColor: '#C09B07'
      }
   },

   scrollbar: {
      barBackgroundColor: '#808083',
      barBorderColor: '#808083',
      buttonArrowColor: '#CCC',
      buttonBackgroundColor: '#606063',
      buttonBorderColor: '#606063',
      rifleColor: '#f4f4f4',
      trackBackgroundColor: '#404043',
      trackBorderColor: '#404043'
   },

   // special colors for some of the
   legendBackgroundColor: 'rgba(255, 255, 255, 0.06)',
   background2: '#',
   dataLabelsColor: '#ff0000',
   textColor: '#C09B07',
   contrastTextColor: '#F0F0F3',
   maskColor: '#f4f4f4'
};

// Apply the theme
//Highcharts.setOptions(Highcharts.theme);