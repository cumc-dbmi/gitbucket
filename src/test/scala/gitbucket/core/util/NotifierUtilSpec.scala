package gitbucket.core.util


import gitbucket.core.service.SystemSettingsService.Smtp
import org.scalatest.FunSpec

class NotifierUtilSpec extends FunSpec  {
  val testSubject = "test"
  val encryptFlag = "#encrypt"
  val whitelist = "ok.com;good.com"
  val mailer = new Mailer(null)

  System.setProperty("encryptFlag", encryptFlag)
  System.setProperty("whitelist", whitelist)

  describe("Mailer send") {
    it("should append encryptFlag to subject if recipient domain not on whitelist") {
      assert(mailer.subjectFor(testSubject, "someone@notok.com").endsWith(encryptFlag))
    }
    it("should NOT append encryptFlag to subject if recipient domain on whitelist") {
      assert(mailer.subjectFor(testSubject, "someone@ok.com") == testSubject)
      assert(mailer.subjectFor(testSubject, "someone@good.com") == testSubject)
    }
  }

}
