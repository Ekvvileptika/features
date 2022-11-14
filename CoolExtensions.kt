//**Simple visibility fields*/
fun View.show(){
    visibility = View.VISIBLE
}
fun View.invisible(){
    visibility = View.INVISIBLE
}
fun View.hide(){
    visibility = View.HIDE
}

//**Visibility extension*/
inline fun View.showIf(condition: View.() -> Boolean){
    if(condition()){
        show()
    } else {
        hide()
    }
}

inline fun View.invisibleIf(condition: View.() -> Boolean){
    if(condition()){
        invisible()
    } else {
        show()
    }
}

inline fun View.hideIf(condition: View.() -> Boolean){
    if(condition()){
        hide()
    } else {
        show()
    }
}

// Typography
fun View.setBackgroundColorRes(@ColorRes color: Int) = seetBackgroundColor(context.getColor(color))

fun TextView.setTextColorRes(@ColorRes color: Int) = setTextColor(context.getColorCompat(color))

fun ImageView.setTint(@ColorRes color: Int) {
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(ContextCompat.getColor(context, color)))
}

