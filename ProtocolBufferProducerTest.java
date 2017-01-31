/**
 * 
 */
package com.priceline.hotelapi.retail.aerospike;

import java.util.Collection;

import org.junit.Test;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Host;
import com.aerospike.client.Key;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.WritePolicy;
import com.priceline.hotelapi.common.refreshable.HotelPropertyRefreshableDataDAO;
import com.priceline.hotelapi.retail.aerospike.genfiles.HotelPropertyProtos.HotelProperty;
import com.priceline.hotelapi.retail.aerospike.genfiles.HotelPropertyProtos.HotelProperty.HotelControlFlag;

/**
 * @author aiyer
 *
 */
public class ProtocolBufferProducerTest {
	@Test
	public void test() {
		Host[] hosts = new Host[] { new Host("nw-aerodb-d01.dqs.pcln.com", 3000), new Host("nw-aerodb-d02.dqs.pcln.com", 3000),
				new Host("nw-aerodb-d03.dqs.pcln.com", 3000), new Host("nw-aerodb-d04.dqs.pcln.com", 3000) };

		AerospikeClient client = new AerospikeClient(new ClientPolicy(), hosts);
		// Initialize policy.
		WritePolicy policy = new WritePolicy();
		policy.timeout = 50; // 50 millisecond timeout.

		
		Collection<com.priceline.hotelapi.common.refreshabledata.dto.HotelProperty> hotels = HotelPropertyRefreshableDataDAO.getInstance().getAllHotels();
		for (com.priceline.hotelapi.common.refreshabledata.dto.HotelProperty hotel : hotels) {
			com.priceline.hotelapi.common.refreshabledata.dto.HotelControlFlag hpControlFlag = hotel.getHotelControlFlag();
			HotelControlFlag.Builder hotelControlFlag = HotelControlFlag.newBuilder();
			hotelControlFlag.setAaaWSPNAgencyenabled(hpControlFlag.isAaaWSPNAgencyenabled());
			hotelControlFlag.setAgdAgencyEnabled(hpControlFlag.isAgdAgencyEnabled());
			hotelControlFlag.setAgdMerchantEnabled(hpControlFlag.isAgdMerchantEnabled());
			hotelControlFlag.setBkgAgencyEnabled(hpControlFlag.isBkgAgencyEnabled());
			hotelControlFlag.setEnetMerchantEnabled(hpControlFlag.isEnetMerchantEnabled());
			hotelControlFlag.setHbsMerchantEnabled(hpControlFlag.isHbsMerchantEnabled());
			hotelControlFlag.setNyopAGDMerchantEnabled(hpControlFlag.isNyopAGDMerchantEnabled());
			hotelControlFlag.setNyopAGDOpaqueEnabled(hpControlFlag.isNyopAGDOpaqueEnabled());
			hotelControlFlag.setNyopENETMerchantEnabled(hpControlFlag.isNyopENETMerchantEnabled());
			hotelControlFlag.setNyopENETOpaqueEnabled(hpControlFlag.isNyopENETOpaqueEnabled());
			hotelControlFlag.setNyopHBSMerchantEnabled(hpControlFlag.isNyopHBSMerchantEnabled());
			hotelControlFlag.setNyopHBSOpaqueEnabled(hpControlFlag.isNyopHBSOpaqueEnabled());
			hotelControlFlag.setNyopPEGSMerchantEnabled(hpControlFlag.isNyopPEGSMerchantEnabled());
			hotelControlFlag.setNyopTRCOpaqueEnabled(hpControlFlag.isNyopTRCOpaqueEnabled());
			hotelControlFlag.setNyopWSPNOpaqueEnabled(hpControlFlag.isNyopWSPNOpaqueEnabled());
			hotelControlFlag.setOpaqueEnabled(hpControlFlag.isOpaqueEnabled());
			hotelControlFlag.setPegsMerchantEnabled(hpControlFlag.isPegsMerchantEnabled());
			hotelControlFlag.setPkgBkgAgencyEnabled(hpControlFlag.isPkgBkgAgencyEnabled());
			hotelControlFlag.setPkgOpaEnabledFlag(hpControlFlag.isPkgOpaEnabledFlag());
			hotelControlFlag.setPkgOpqHBSEnabled(hpControlFlag.isPkgOpqHBSEnabled());
			hotelControlFlag.setPkgOpqTRCEnabled(hpControlFlag.isPkgOpqTRCEnabled());
			hotelControlFlag.setRetailEnabled(hpControlFlag.isRetailEnabled());
			hotelControlFlag.setSemiOpaqueEnabled(hpControlFlag.isSemiOpaqueEnabled());
			hotelControlFlag.setSopqAGDMerchantEnabled(hpControlFlag.isSopqAGDMerchantEnabled());
			hotelControlFlag.setSopqAGDOpaqueEnabled(hpControlFlag.isSopqAGDOpaqueEnabled());
			hotelControlFlag.setSopqDiscENETEnabled(hpControlFlag.isSopqDiscENETEnabled());
			hotelControlFlag.setSopqENETEnabled(hpControlFlag.isSopqENETMerchantEnabled());
			hotelControlFlag.setSopqENETMerchantEnabled(hpControlFlag.isSopqENETMerchantEnabled());
			hotelControlFlag.setSopqENETOpaqueEnabled(hpControlFlag.isSopqENETOpaqueEnabled());
			hotelControlFlag.setSopqHBSMerchantEnabled(hpControlFlag.isSopqHBSMerchantEnabled());
			hotelControlFlag.setSopqPEGSMerchantEnabled(hpControlFlag.isSopqPEGSMerchantEnabled());
			hotelControlFlag.setSopqTRCOpaqueEnabled(hpControlFlag.isSopqTRCOpaqueEnabled());
			hotelControlFlag.setSopqWSPNOpaqueEnabled(hpControlFlag.isSopqWSPNOpaqueEnabled());
			if (hpControlFlag.getWspnRateAccessCode() != null) {
				hotelControlFlag.setWspnRateAccessCode(hpControlFlag.getWspnRateAccessCode());
			}
			hotelControlFlag.build();

			HotelProperty.Builder hotel1 = HotelProperty.newBuilder();
			hotel1.setHotelId(hotel.getHotelId());
			hotel1.setHotelControlFlag(hotelControlFlag);
			hotel1.setBeddingEnabledFlag(hotel.isBeddingEnabledFlag());
			if (hotel.getBkgHotelId() != null) {
				hotel1.setBkgHotelId(hotel.getBkgHotelId());
			}

			if (hotel.getBrandId() != null) {
				hotel1.setBrandId(hotel.getBrandId());
			}
			if (hotel.getBrandName() != null) {
				hotel1.setBrandName(hotel.getBrandName());
			}
			if (hotel.getChainCode() != null) {
				hotel1.setChainCode(hotel.getChainCode());
			}
			hotel1.setChildrenAllowedFlag(hotel.isChildrenAllowedFlag());

			if (hotel.getCityId() != null) {
				hotel1.setCityId(hotel.getCityId());
			}
			if (hotel.getClusterIdV2() != null) {
				hotel1.setClusterIdV2(hotel.getClusterIdV2());
			}
			if (hotel.getClusterName() != null) {
				hotel1.setClusterName(hotel.getClusterName());
			}
			if (hotel.getCountryCode() != null) {
				hotel1.setCountryCode(hotel.getCountryCode());
			}
			hotel1.setDisplayOnExpressDealsFlag(hotel.isDisplayOnExpressDealsFlag());

			if (hotel.getHbsHotelId() != null) {
				hotel1.setHbsHotelId(hotel.getHbsHotelId());
			}
			/*if (hotel.getPclnHotelName() != null) {
				hotel1.setHotelName(hotel.getPclnHotelName());
			}*/
			hotel1.setInclusiveRateFlag(hotel.isInclusiveRateFlag());
			hotel1.setTaxIncludedFlag(hotel.isTaxIncludedFlag());

			if (hotel.getJavaTimezoneId() != null) {
				hotel1.setJavaTimezoneId(hotel.getJavaTimezoneId());
			}
			if (hotel.getLatitude() != null) {
				hotel1.setLatitude(hotel.getLatitude());
			}
			if (hotel.getLongitude() != null) {
				hotel1.setLongitude(hotel.getLongitude());
			}
			if (hotel.getMaxRoomResidents() != null) {
				hotel1.setMaxRoomResidents(hotel.getMaxRoomResidents());
			}
			if (hotel.getMinCheckInAge() != null) {
				hotel1.setMinCheckInAge(hotel.getMinCheckInAge());
			}

			hotel1.setNonPclnPaymentSupported(hotel.isNonPclnPaymentSupported());

			if (hotel.getPegChainCode() != null) {
				hotel1.setPegChainCode(hotel.getPegChainCode());
			}
			if (hotel.getPegPropertyCode() != null) {
				hotel1.setPegPropertyCode(hotel.getPegPropertyCode());
			}

			hotel1.setSamedayBookingAllowed(hotel.isSamedayBookingAllowed());

			if (hotel.getStarRating() != null) {
				hotel1.setStarRating(hotel.getStarRating());
			}
			if (hotel.getTrcHotelId() != null) {
				hotel1.setTrcHotelId(hotel.getTrcHotelId());
			}

			hotel1.setUsePclnDescriptionFlag(hotel.isUsePclnDescriptionFlag());
			if (hotel.getWorldspanChainCode() != null) {
				hotel1.setWorldspanChainCode(hotel.getWorldspanChainCode());
			}
			if (hotel.getZoneId() != null) {
				hotel1.setZoneId(hotel.getZoneId());
			}
			HotelProperty serHotel = hotel1.build();
			Key key = new Key("hotel", "hotelset", hotel1.getHotelId());
			Bin bin1 = new Bin("mybin", serHotel);
			// Bin bin2 = new Bin("mybin1", "myvalu1e");
			client.put(policy, key, bin1);
			
			System.out.println(hotel1.getHotelName());
			break;

		}
		
		client.close();
		
	}
}
