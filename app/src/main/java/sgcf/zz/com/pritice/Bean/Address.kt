package sgcf.zz.com.pritice.Bean

/**
 * Created by admin
 * Date:  2018/3/28.
 * Copyright on 2018 Henan Bijia Industrial Co., Ltd.
 * Desc:Pritice
 */
data class Address(var id: String) {
    var content: String = ""
    var groupId: Long = 0L
    var siteName: String = ""
    var siteSlug: String = ""
    var mobileUrl: String = ""
    var authorName: String = ""
    var duplicateId: Int = 0
    var publishDate: String = ""

    init {

    }
}