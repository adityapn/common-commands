package com.pnaditya.problems;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
        public static Double getDistance(Double firstLat, Double firstLng, Double secLat, Double secLng) {
                    double dLat = Math.toRadians(secLat - firstLat);
                            double dLng = Math.toRadians(secLng - firstLng);
                                    firstLat = Math.toRadians(firstLat);
                                            secLat = Math.toRadians(secLat);
                                                    double a = (Math.sin(dLat / 2) * Math.sin(dLat / 2))
                                                                        + (Math.sin(dLng / 2) * Math.sin(dLng / 2)) * (Math.cos(firstLat) * Math.cos(secLat));
                                                            double c = 2 * Math.asin(Math.sqrt(a));
                                                                    return 6372.8 * c * 1300;
                                                                        }

            public static Double getDuration(Double firstLat, Double firstLng, Double secLat, Double secLng, Double speed) {
                        Double etaMinutes = 55d;
                                Double distance = getDistance(firstLat, firstLng, secLat, secLng);
                                        Double duration = Math.ceil(((distance + 1) / speed) * etaMinutes);
                                                duration = distance > 1000 ? duration + 5 : duration + 1;
                                                        return duration;
                                                            }

                public static String getDateTime(Timestamp timestamp) {
                            String day = String.valueOf(timestamp.getDate());
                                    String month = String.valueOf(timestamp.getMonth() + 1);
                                            String year = String.valueOf(timestamp.getYear() + 1900);
                                                    Integer hours = timestamp.getHours();
                                                            Integer minutes = timestamp.getMinutes();
                                                                    String amOrpm = "";

                                                                            day = day.length() == 1 ? "0" + day : day;
                                                                                    month = month.length() == 1 ? "0" + month : month;
                                                                                            amOrpm = hours > 12 ? "PM" : "AM";
                                                                                                    hours = hours > 12 ? hours % 12 : hours;

                                                                                                            String hour = hours < 10 ? "0" + hours : String.valueOf(hours);
                                                                                                                    String minute = minutes < 10 ? "0" + hours : String.valueOf(minutes);
                                                                                                                            String format = day + "-" + month + "-" + year + " " + hour + ":" + minute + " " + amOrpm;
                                                                                                                                    return format;
                                                                                                                                        }

                    public String asCsv(List<Long> values) {
                                if (values.size() == 0) {
                                                return "";
                                                        }
                                        StringBuilder csv = new StringBuilder();
                                                int counter = 0;
                                                        for (; counter < values.size() - 1; counter++) {
                                                                        csv.append(values.get(counter) + ",");
                                                                                }
                                                                csv.append(values.get(counter));
                                                                        return csv.toString();
                                                                            }
                        
                        public static Boolean isPresentInPolygon(List<Double> polygon, Double latitude, Double longitude) {
                                    Integer totalPoints = polygon.size();
                                            if (totalPoints < 2 || totalPoints % 2 != 0) {          
                                                            System.err.println("there have to be even number of points");
                                                                        return false;
                                                                                }

                                                    Double pointXFirst = polygon.get(0);
                                                            Double pointYFirst = polygon.get(1);
                                                                    Boolean inside = false;
                                                                            for (int counter = 0; counter < totalPoints; counter = counter + 2) {
                                                                                            Double pointXSecond = polygon.get(counter);
                                                                                                        Double pointYSecond = polygon.get(counter + 1);         
                                                                                                                    if (longitude > Math.min(pointYFirst, pointYSecond) && longitude <= Math.max(pointYFirst, pointYSecond)
                                                                                                                                                && latitude <= Math.max(pointXFirst, pointXSecond)) {
                                                                                                                                        Double xints = 0d;
                                                                                                                                                        if (pointYFirst != pointYSecond) {
                                                                                                                                                                                xints = (longitude - pointYFirst) * (pointXSecond - pointXFirst) / (pointYSecond - pointYFirst)
                                                                                                                                                                                                                + pointXFirst;
                                                                                                                                                                                                    
                                                                                                                                                                                                }
                                                                                                                                                                        if (pointXFirst == pointXSecond || latitude <= xints) {
                                                                                                                                                                                                inside = !inside;
                                                                                                                                                                                                                }
                                                                                                                                                                                        
                                                                                                                                                                                    }
                                                                                                                                pointXFirst = pointXSecond;
                                                                                                                                            pointYFirst = pointYSecond;             
                                                                                                                                                    }
                                                                                    return inside;
                                                                                        }

                            public static void main(String[] args) {
                                        Double[] points = {12.938063500000002,77.6079369,12.936181300000001,77.6077116,12.9320405,77.6136661,12.9270213,77.6166486,12.9212908,77.6202536,12.9228803,77.6235152,12.9242606,77.6299954,12.924553400000002,77.6312399,12.921761399999998,77.63201240000001,12.9213117,77.6324416,12.920203300000004,77.6384497,12.9238841,77.6399517,12.925222600000001,77.6373339,12.926770300000001,77.6364756,12.927899600000002,77.6348448,12.9295937,77.63239860000002,12.930116500000002,77.6309824,12.930074600000001,77.6305533,12.9317896,77.63012410000002,12.933399900000001,77.6307249,12.933556700000002,77.6307034,12.934079599999999,77.6294374,12.9343201,77.6297593,12.9347697,77.6294696,12.9356272,77.6286436,12.936327699999998,77.6292872,12.937132900000002,77.6297808,12.9360559,77.6345444,12.9386072,77.6326561,12.942329700000002,77.6309824,12.9460103,77.6290083,12.948478,77.6275492,12.9493772,77.6269484,12.9502346,77.6257038,12.950631900000001,77.6243091,12.95036,77.6241589,12.949188899999998,77.624073,12.949188899999998,77.6228285,12.9502345,77.623601,12.950799100000001,77.6211977,12.951782,77.6188803,12.9460521,77.6158762,12.947223200000002,77.613945,12.9466377,77.6136017,12.9452993,77.6139879,12.945132000000001,77.6145029,12.943877199999996,77.6133013,12.941995099999998,77.6133442,12.943459000000002,77.6098251,12.9442118,77.60750770000001,12.9445464,77.6061773,12.9440445,77.6063061,12.944170000000002,77.6051474,12.938063500000002,77.6079369};
                                                List<Double> polygon = Arrays.asList(points);       
                                                        Double inZoneLatitude = 12.933664;
                                                                Double inZoneLongitude = 77.614902;
                                                                        
                                                                        Double notInZoneLatitude = 12.954127;
                                                                                Double notInZoneLongitude = 77.603372;
                                                                                        
                                                                                        
                                                                                        System.out.println(isPresentInPolygon(polygon, notInZoneLatitude, notInZoneLongitude));
                                                                                            }
}

