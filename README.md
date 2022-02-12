# EarthquakeFilter
Filters the earthquakes according to date and country thanks to earthquake.usgs

#INPUTS
  - The number of days should not exceed 18.12.2021 because there is no data on the earthquake.usgs site before that.
  - If the keyword entered into the program is substring in the place attribute of the earthquakes in the json file, the earthquake is listed.
  - Input must consist of english characters
  - Upper case and lower case character will not miss earthquakes, you can use both of them

#OUTPUTS
  - Example input: 56 and TURKEY (12.02.2022)
  - Example output format:
    Place                                                            Magnitude                Date                                    Time(GMT+3)              
    *****                                                            *********                ****                                    ***********              
    19 km SSE of GÃ¼vercinlik, Turkey                                4,10                     02/11/22                                19:18:08                 
    11 km SSW of Kocadere, Turkey                                    4,10                     02/06/22                                15:07:52                 
    6 km SE of Derbent, Turkey                                       4,50                     01/31/22                                12:30:37                 
    7 km SSE of Yayladere, Turkey                                    4,70                     01/31/22                                03:49:56                 
    17 km E of Dursunbey, Turkey                                     4,70                     01/22/22                                22:31:43                 
    6 km WSW of Sar?o?lan, Turkey                                    5,10                     01/18/22                                02:28:57                 
    42 km SW of Ã–lÃ¼deniz, Turkey                                   4,10                     01/17/22                                01:16:17                 
    67 km SSW of Okurcalar, Turkey                                   5,10                     01/05/22                                06:21:16                 
    27 km N of Van, Turkey                                           4,00                     12/24/21                                05:26:40  
   
  - GÃ¼vercinlik, Ã–lÃ¼deniz these typos are due to GeoJSON itself. Because of using Turkish letters.
