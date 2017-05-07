package me.hagen.ssh.dao;

import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.shape.Rectangle;

public class Geography {
	public static void main(String args[]){
		// �ƶ��豸��γ��
		float lon = 116.312528f, lat = 39.983733f;
		
		// ǧ��
		int radius = 1;

		SpatialContext geo = SpatialContext.GEO;
		Rectangle rectangle = geo.getDistCalc().calcBoxByDistFromPt(
		        geo.makePoint(lon, lat), radius * DistanceUtils.KM_TO_DEG, geo, null);
		System.out.println(rectangle.getMinX() + "-" + rectangle.getMaxX());// ���ȷ�Χ
		System.out.println(rectangle.getMinY() + "-" + rectangle.getMaxY());// γ�ȷ�Χ
		
		
//		
//		/**���������ص�����֮��ľ������
//		 * */
//		// �ƶ��豸��γ��
//		double lon1 = 116.3125333347639, lat1 = 39.98355521792821;
//		// �̻���γ��
//		double lon2 = 116.312528, lat2 = 39.983733;
//
//		SpatialContext geo = SpatialContext.GEO;
//		double distance = geo.calcDistance(geo.makePoint(lon1, lat1), geo.makePoint(lon2, lat2)) 
//		    * DistanceUtils.DEG_TO_KM;
//		System.out.println(distance);// KM
		
	}
}
