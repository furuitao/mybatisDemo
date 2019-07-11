import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dongChen on 2017/2/21.
 */
public class HumpUtils {

    private static final Pattern linePattern = Pattern.compile("_(\\w)");

    private static final Pattern humpPattern = Pattern.compile("[A-Z]");

    /** 下划线转驼峰 */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /** 驼峰转下划线,效率比上面高 */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args){
        String[] a = new String[]{

                "apply_source_count=int(10)",
                "bank_card_hit_black=tinyint(1)",
                "call_count_in_3_month=int(10)",
                "close_friend_gt_10_hit_apply_count=int(10)",
                "close_friend_gt_10_hit_black_count=int(10)",
                "close_friend_gt_10_hit_loan_count=int(10)",
                "close_friend_gt_10_hit_reject_count=int(10)",
                "close_friend_gt_30_hit_apply_count=int(10)",
                "close_friend_gt_30_hit_black_count=int(10)",
                "close_friend_gt_30_hit_loan_count=int(10)",
                "close_friend_gt_30_hit_reject_count=int(10)",
                "close_friend_gt_5_hit_apply_count=int(10)",
                "close_friend_gt_5_hit_black_count=int(10)",
                "close_friend_gt_5_hit_loan_count=int(10)",
                "close_friend_gt_5_hit_reject_count=int(10)",
                "contacts_hit_apply_count=int(10)",
                "contacts_hit_apply_in_30_days_count=int(10)",
                "contacts_hit_black_count=int(10)",
                "contacts_hit_loan_count=int(10)",
                "contacts_hit_reject_count=int(10)",
                "dial_hit_apply_count=int(10)",
                "dial_hit_black_count=int(10)",
                "dial_hit_loan_count=int(10)",
                "dial_hit_reject_count=int(10)",
                "dialed_hit_apply_count=int(10)",
                "dialed_hit_black_count=int(10)",
                "dialed_hit_loan_count=int(10)",
                "dialed_hit_reject_count=int(10)",
                "hit_black_count=int(10)",
                "id_no_hit_black=tinyint(1)",
                "imei_hit_black=tinyint(1)",
                "is_name_same=tinyint(1)",
                "loan_source_count=int(10)",
                "mobile_count_in_3_month=int(10)",
                "mobile_hit_black=tinyint(1)",
                "operator_model_score=decimal(20,15)",
                "reject_source_count=int(10)"

        };
        for (String s : a) {
            String[] split = s.split("=");
            String column = split[0];
            String prop = lineToHump(column);
            String type = "";
            String dbtype = split[1].toUpperCase();
            if(dbtype.contains("INT")){
                type = "Integer";
            }else if(dbtype.contains("DOUBLE")){
                type = "Double";
            }else if(dbtype.contains("DECIMAL")){
                type = "BigDecimal";
            }else if(dbtype.contains("VARCHAR")){
                type = "String";
            }else if(dbtype.contains("TEXT")){
                type = "String";
            }else if(dbtype.contains("DATE")){
                type = "Date";
            }
            if(column.matches(".*\\d.*")) {
                System.out.println("@Column(name = \"" + column + "\")");
            }
            System.out.println("private "+type+" "+prop + ";");
        }
    }
}
