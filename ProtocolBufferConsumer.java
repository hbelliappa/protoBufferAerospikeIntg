/**
 * 
 */
package com.priceline.hotelapi.retail.aerospike;

import org.junit.Test;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Host;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.WritePolicy;
import com.priceline.hotelapi.retail.aerospike.genfiles.HotelPropertyProtos.HotelProperty;

/**
 * @author aiyer
 *
 */
public class ProtocolBufferConsumer {
	@Test
	public void test() {
		Host[] hosts = new Host[] { new Host("nw-aerodb-d01.dqs.pcln.com", 3000), new Host("nw-aerodb-d02.dqs.pcln.com", 3000),
				new Host("nw-aerodb-d03.dqs.pcln.com", 3000), new Host("nw-aerodb-d04.dqs.pcln.com", 3000) };

		AerospikeClient client = new AerospikeClient(new ClientPolicy(), hosts);
		// Initialize policy.
		WritePolicy policy = new WritePolicy();
		policy.timeout = 50; // 50 millisecond timeout.

		
		Key key = new Key("hotel", "hotelset", 187102l);
		Record record = client.get(policy, key);
		HotelProperty hotel3 = (HotelProperty) record.getValue("mybin");
		System.out.println(hotel3.getHotelName());
		
		client.close();
		
	}
}
