/**
 * 
 */
package com.priceline.hotelapi.retail.aerospike;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
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
public class AeroSpikeTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Host[] hosts = new Host[] { new Host("nw-aerodb-d01.dqs.pcln.com", 3000), new Host("nw-aerodb-d02.dqs.pcln.com", 3000),
				new Host("nw-aerodb-d03.dqs.pcln.com", 3000), new Host("nw-aerodb-d04.dqs.pcln.com", 3000) };

		AerospikeClient client = new AerospikeClient(new ClientPolicy(), hosts);
		// Initialize policy.
		WritePolicy policy = new WritePolicy();
		policy.timeout = 50; // 50 millisecond timeout.

		Key key = new Key("hotel", "hotelset", 187102);

		HotelProperty hotel2 = HotelProperty.parseFrom(new FileInputStream(
				"C:\\projects\\workspaceneon\\hotels-retail-api-module\\api-jar\\src\\main\\java\\com\\priceline\\hotelapi\\retail\\aerospike\\genfiles\\hotelPropSample"));

		Bin bin1 = new Bin("mybin", hotel2);
		// Bin bin2 = new Bin("mybin1", "myvalu1e");
		client.put(policy, key, bin1);

		Record record = client.get(policy, key);
		HotelProperty hotel3 = (HotelProperty) record.getValue("mybin");

		// client.delete(policy, key);

		client.close();
	}
}
