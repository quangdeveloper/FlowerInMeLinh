package vn.chohoa.flower.util;

public interface Constant {

    interface Common {

    }

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
            String C403 = "403";

            String C800 = "800";

            String C809 = "C809";
            String ERROR = "999";


        }

        interface MESSAGE {

            String OK = "Thực hiện thành công";

            String C400 = "Bad gateway";

            String C403_EDIT_MESSAGE = "Không có quyền chỉnh sửa nội dung";

            String C400_DATA = "Dữ liệu rỗng";
            String C404 = "Không tìm thấy dữ liệu";
            String C404_DELETE = "Dữ liệu không tồn tại";
            String C404_FLOWER_CATEGORY = "Không tìm thấy loại hoa";
            String C404_FARM = "Không tìm thấy vườn hoa";
            String C404_FLOWER= "Không tìm thấy sản phẩm hoa";
            String C404_ROLE= "Không tìm thấy được quyền này";
            String C404_USER = "Không tìm thấy người dùng";
            String C404_SENDER = "Không tìm thấy người gửi";
            String C404_RECEIVER = "Không tìm thấy người nhận";
            String C404_MOJI = "Không tìm thấy Moji";
            String C404_CONVERSATION = "Hội thoại không tồn tại";
            String C404_EDIT_USER = "Người sửa không tồn tại";
            String C404_VIEW_USER = "Người xem không tồn tại";

            String C409 = "Tài nguyên đã tồn tại";
            String C409_FLOWER = "Sản phẩm hoa này đã tồn tại";
            String C409_TYPE_FLOWER = "Danh mục hoa này đã tồn tại";
            String C409_ROLE = "Quyền này đã tồn tại";
            String C409_USER = "Tài khoản đã tồn tại";

            String C800_RECEIVER = "Không xác định người nhận tin nhắn";

            String C809 = "Không thể xem tin nhắn đã xóa";
            String ERROR = "Lỗi không xác định";


        }

        interface JSON_KEY {
            String RETURN_VALUE = "Return value";
        }
    }

}
