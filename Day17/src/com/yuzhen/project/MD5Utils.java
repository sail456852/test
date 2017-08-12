package com.yuzhen.project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Utils {

    /**
     * 鑾峰彇MD5鍔犲瘑
     * 
     * @param pwd
     *            闇�瑕佸姞瀵嗙殑瀛楃涓�
     * @return String瀛楃涓� 鍔犲瘑鍚庣殑瀛楃涓�
     */
    public static String encode(String pwd) {
        try {
            // 鍒涘缓鍔犲瘑瀵硅薄
            MessageDigest digest = MessageDigest.getInstance("md5");

            // 璋冪敤鍔犲瘑瀵硅薄鐨勬柟娉曪紝鍔犲瘑鐨勫姩浣滃凡缁忓畬鎴�
            byte[] bs = digest.digest(pwd.getBytes());
            // 鎺ヤ笅鏉ワ紝鎴戜滑瑕佸鍔犲瘑鍚庣殑缁撴灉锛岃繘琛屼紭鍖栵紝鎸夌収mysql鐨勪紭鍖栨�濊矾璧�
            // mysql鐨勪紭鍖栨�濊矾锛�
            // 绗竴姝ワ紝灏嗘暟鎹叏閮ㄨ浆鎹㈡垚姝ｆ暟锛�
            String hexString = "";
            for (byte b : bs) {
                // 绗竴姝ワ紝灏嗘暟鎹叏閮ㄨ浆鎹㈡垚姝ｆ暟锛�
                // 瑙ｉ噴锛氫负浠�涔堥噰鐢╞&255
                /*
                 * b:瀹冩湰鏉ユ槸涓�涓猙yte绫诲瀷鐨勬暟鎹�(1涓瓧鑺�) 255锛氭槸涓�涓猧nt绫诲瀷鐨勬暟鎹�(4涓瓧鑺�)
                 * byte绫诲瀷鐨勬暟鎹笌int绫诲瀷鐨勬暟鎹繘琛岃繍绠楋紝浼氳嚜鍔ㄧ被鍨嬫彁鍗囦负int绫诲瀷 eg: b: 1001 1100(鍘熷鏁版嵁)
                 * 杩愮畻鏃讹細 b: 0000 0000 0000 0000 0000 0000 1001 1100 255: 0000
                 * 0000 0000 0000 0000 0000 1111 1111 缁撴灉锛�0000 0000 0000 0000
                 * 0000 0000 1001 1100 姝ゆ椂鐨則emp鏄竴涓猧nt绫诲瀷鐨勬暣鏁�
                 */
                int temp = b & 255;
                // 绗簩姝ワ紝灏嗘墍鏈夌殑鏁版嵁杞崲鎴�16杩涘埗鐨勫舰寮�
                // 娉ㄦ剰锛氳浆鎹㈢殑鏃跺�欐敞鎰廼f姝ｆ暟>=0&&<16锛岄偅涔堝鏋滀娇鐢↖nteger.toHexString()锛屽彲鑳戒細閫犳垚缂哄皯浣嶆暟
                // 鍥犳锛岄渶瑕佸temp杩涜鍒ゆ柇
                if (temp < 16 && temp >= 0) {
                    // 鎵嬪姩琛ヤ笂涓�涓��0鈥�
                    hexString = hexString + "0" + Integer.toHexString(temp);
                } else {
                    hexString = hexString + Integer.toHexString(temp);
                }
            }
            return hexString;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}