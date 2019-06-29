package objects;

import java.util.Arrays;

public enum Enemies {
D66,D4,D8,D10,t04,t05,t06,t07,t08,t09,
D65,t11,t12,t13,t14,t15,t16,t17,t18,t19,
D64,t21,t22,t23,t24,t25,t26,t27,t28,t29,
D63,t31,t32,t33,t34,t35,t36,t37,t38,t39,
D62,t41,t42,t43,t44,t45,t46,t47,t48,t49,
D61,t51,t52,t53,t54,t55,t56,t57,t58,t59;
public static Enemies[][] arrayOfDefault(int length) {
    Enemies[][] result = new Enemies[length][length];
    for(Enemies[] row:result){
    	Arrays.fill(row, t59);
    }
    return result;
	}
}
