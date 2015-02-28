package ch.bittailor.iot.san.nrf24;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public interface RfDeviceController {
	
	
	void configure(Configuration configuration);
	boolean write(RfPipe iPipe, ByteBuffer iPacket);
	void startListening(Listener listener);
	void stopListening();

	
	public static interface Listener {
		void packageReceived(RfPipe pipe, ByteBuffer packet);
	}
	
	public static class Configuration {
		public int mAutoRetransmitDelay =  0x15;
		public int mChannel = 0x10;
		private final Map<RfPipe,PipeConfiguration> mPipeConfigurations;
		
		public Configuration() {
			mPipeConfigurations = new HashMap<RfPipe,PipeConfiguration>();
			for (RfPipe pipe : RfPipe.values()) {
				mPipeConfigurations.put(pipe, new PipeConfiguration());
			}
		}

		int autoRetransmitDelay() {
			return mAutoRetransmitDelay;
		}

		PipeConfiguration pipeConfiguration(RfPipe pipe) {
			return mPipeConfigurations.get(pipe);
		}

		public static class PipeConfiguration {
			public boolean mEnabled;
			public RfAddress mAddress;
		};		
	}
}
