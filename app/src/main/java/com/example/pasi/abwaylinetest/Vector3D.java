package com.example.pasi.abwaylinetest;

public class Vector3D {
    public double x, y, z;

    Vector3D() {
        x = 0;
        y = 0;
        z = 0;
    }

    Vector3D(EarthSurfaceVector3D surfaceVector) {
        x = surfaceVector.getX();
        y = surfaceVector.getY();
        z = surfaceVector.getZ();
    }

    double length() {
        return(Math.sqrt(x*x+y*y+z*z));
    }

    void normalize(){
        double L = length();
        if (L != 0)
        {
            x /= L;
            y /= L;
            z /= L;
        }
    }

    double dotProduct(Vector3D OtherVec){
        return(x * OtherVec.x + y * OtherVec.y + z * OtherVec.z);
    }

    Vector3D crossProduct(Vector3D otherVec) {
        Vector3D retVec = new Vector3D();

        retVec.x = y * otherVec.z - z * otherVec.y;
        retVec.y = z * otherVec.x - x * otherVec.z;
        retVec.z = x * otherVec.y - y * otherVec.x;

        return retVec;
    }
}
