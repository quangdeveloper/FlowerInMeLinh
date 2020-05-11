package vn.chohoa.flower.util;

public interface Constant {

    interface Common {

    }



    /**
     *
     */
    interface RESPONSE {
        /**
         * lỗi này return ra 1 danh sách kết quả
         */
        String RS = "rs";
        String PO_CODE = "code";
        String PO_MSG = "message";

        interface CODE {

            String OK = "200";
            String C400 = "400";
            String C404 = "404";
            String C409 = "409";
            String ERROR = "999";

        }

        interface MESSAGE {

            String OK = "Thực hiện thành công";
            String C400 = "Bad gateway";
            String C404 = "Không tìm thấy dữ liệu";
            String C404_DELETE = "Dữ liệu không tồn tại";
            String C404_FLOWER_CATEGORY = "Không tìm thấy loại hoa";
            String C404_FARM = "Không tìm thấy vườn hoa";
            String C404_FLOWER= "Không tìm thấy sản phẩm hoa";
            String C404_ROLE= "Không tìm thấy được quyền này";
            String C404_USER = "Không tìm thấy người dùng";
            String C409 = "Tài nguyên đã tồn tại";
            String C409_FLOWER = "Sản phẩm hoa này đã tồn tại";
            String C409_ROLE = "Quyền này đã tồn tại";
            String C409_USER = "Tài khoản đã tồn tại";
            String ERROR = "Lỗi không xác định";


        }

        interface JSON_KEY {
            String RETURN_VALUE = "Return value";
        }
    }

}
