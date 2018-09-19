package kipspark2

object Helpers {

  object Date {

    //def apply(x:String):String = unapply(x) match { case Some(t) => t ; case None => throw new Error("not a date format") }

    def unapply(str: String): Option[String] = { //str = 2018-12-25
      val parts = str split "-"
      if (parts.length == 3 && parts(0).length == 4 && parts(1).length == 2 && parts(2).length == 2) Some(str) else None
    }
  }

  //"Enter time range in hh:mm-hh:mm format"

  object TimeRange {
    def unapply(str:String): Option[(String,String)] = {
      val strs:Array[String] = str.split("-") flatMap (_.split(":"))
      val parts = strs map(_.toInt)
      def isMinute(m:Int):Boolean = 0 <= m && m < 60
      if(parts.length == 4 && (parts(0) <= parts(2)) && isMinute(parts(1)) && isMinute(parts(3))) Some( strs(0)+":"+strs(1), strs(2)+":"+strs(3) ) else None
    }
  }
}
