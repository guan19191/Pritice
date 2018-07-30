package sgcf.zz.com.pritice.widget;


import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;


import sgcf.zz.com.pritice.R;

/**
 * 自定义loadingDialog
 */
public class LoadingDialog {

    private Context context;
    private AlertDialog dialog;

    public LoadingDialog(Context context) {
        this.context = context;
    }

    public void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context, R.style.loading_dialog);
        View view = View.inflate(context, R.layout.dialog_loading, null);
        TextView tv_dialog_message = view.findViewById(R.id.tv_dialog_message);
        tv_dialog_message.setText("拼命加载中...");
        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    public void showDialog(String msg) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context, R.style.loading_dialog);
        View view = View.inflate(context, R.layout.dialog_loading, null);
        TextView tv_dialog_message = view.findViewById(R.id.tv_dialog_message);
        tv_dialog_message.setText(msg);
        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
