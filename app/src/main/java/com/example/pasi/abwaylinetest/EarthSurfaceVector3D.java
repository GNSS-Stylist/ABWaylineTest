package com.example.pasi.abwaylinetest;

import android.location.Location;

public class EarthSurfaceVector3D {
    private double x, y, z;

    public EarthSurfaceVector3D(Location location) {
        setLocation(location);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setLocation(Location location) {
        // coordinate-system:
        // Y-axis is the (rotational) axis of Earth (North = positive)
        // Z-axis points to Greenwich meridian (Greenwich direction = positive)
        // positive X-axis points to east.

        double latitudeRad = (2 * Math.PI) * location.getLatitude() / 360;
        double longitudeRad = (2 * Math.PI) * location.getLongitude() / 360;

        x = 0;
        y = 0;
        z = 1;

        rotateX(-latitudeRad);
        rotateY(longitudeRad);

        // Calculate xyz-coordinates using WGS84-reference ellipsoid

        x *= 6378137.0;
        y *= 6356752.314245;    // Earth is indeed flat! (by about 0,3 % that is)
        z *= 6378137.0;
    }

    public double getDistance(Location location) {
        EarthSurfaceVector3D location2vector = new EarthSurfaceVector3D(location);

        return Math.sqrt(((location2vector.getX() - x) * (location2vector.getX() - x)) +
                ((location2vector.getY() - y) * (location2vector.getY() - y)) +
                ((location2vector.getZ() - z) * (location2vector.getZ() - z)));
    }

    private void rotateX(double radians) {
        double TempY = y;
        double TempZ = z;

        y = TempY * Math.cos(radians) - TempZ * Math.sin(radians);
        z = TempY * Math.sin(radians) + TempZ * Math.cos(radians);
    }

    private void rotateY(double radians)
    {
        double TempX = x;
        double TempZ = z;

        z = TempZ * Math.cos(radians) - TempX * Math.sin(radians);
        x = TempZ * Math.sin(radians) + TempX * Math.cos(radians);
    }

}
