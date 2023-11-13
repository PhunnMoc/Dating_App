package Handle;

import java.util.Base64;


public class ImageHandle {
	public static String byteArrayToImage(byte[] imageData) {
        if (imageData == null)
        	return null;
        String base64Image = Base64.getEncoder().encodeToString(imageData);
		return base64Image;
    }

}
