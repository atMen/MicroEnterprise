package com.tcrj.micro.checkUpdata;

/**
 * 软件版本信息对象
 * 
 * @author Royal
 * 
 */
public class VersionInfo {
	/**
	 * State : 1
	 * Msg : 操作成功
	 * Entity : {"Versions":"15","ApkName":"TCYun.apk","AndUrl":"http://113.200.26.66:8000/DTKCRM/Api/apk/TCYun.apk","IosUrl":"http://www.pgyer.com/7IFy","PublicTime":"2017-12-29","Remark":"\r\n    新版本发布,赶紧下载吧\r\n更新日志：\r\n1.请确定2017年12月29日的日报已写后再更新\r\n2.日报功能进行更新\r\n  "}
	 */

	private int State;
	private String Msg;
	private EntityBean Entity;

	public int getState() {
		return State;
	}

	public void setState(int State) {
		this.State = State;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String Msg) {
		this.Msg = Msg;
	}

	public EntityBean getEntity() {
		return Entity;
	}

	public void setEntity(EntityBean Entity) {
		this.Entity = Entity;
	}

	public static class EntityBean {
		/**
		 * Versions : 15
		 * ApkName : TCYun.apk
		 * AndUrl : http://113.200.26.66:8000/DTKCRM/Api/apk/TCYun.apk
		 * IosUrl : http://www.pgyer.com/7IFy
		 * PublicTime : 2017-12-29
		 * Remark :
		 新版本发布,赶紧下载吧
		 更新日志：
		 1.请确定2017年12月29日的日报已写后再更新
		 2.日报功能进行更新

		 */

		private String Versions;
		private String ApkName;
		private String AndUrl;
		private String IosUrl;
		private String PublicTime;
		private String Remark;

		public String getVersions() {
			return Versions;
		}

		public void setVersions(String Versions) {
			this.Versions = Versions;
		}

		public String getApkName() {
			return ApkName;
		}

		public void setApkName(String ApkName) {
			this.ApkName = ApkName;
		}

		public String getAndUrl() {
			return AndUrl;
		}

		public void setAndUrl(String AndUrl) {
			this.AndUrl = AndUrl;
		}

		public String getIosUrl() {
			return IosUrl;
		}

		public void setIosUrl(String IosUrl) {
			this.IosUrl = IosUrl;
		}

		public String getPublicTime() {
			return PublicTime;
		}

		public void setPublicTime(String PublicTime) {
			this.PublicTime = PublicTime;
		}

		public String getRemark() {
			return Remark;
		}

		public void setRemark(String Remark) {
			this.Remark = Remark;
		}
	}

//	// 版本描述字符串
//	private String version;
//	// 版本更新时间
//	private String updateTime;
//	// 新版本更新下载地址
//	private String downloadURL;
//	// 更新描述信息
//	private String displayMessage;
//	// 版本号
//	private String versionCode;
//	// apk名称
//	private String apkName;
//
//
//
//	public String getVersion() {
//		return version;
//	}
//
//	public void setVersion(String version) {
//		this.version = version;
//	}
//
//	public String getUpdateTime() {
//		return updateTime;
//	}
//
//	public void setUpdateTime(String updateTime) {
//		this.updateTime = updateTime;
//	}
//
//	public String getDownloadURL() {
//		return downloadURL;
//	}
//
//	public void setDownloadURL(String downloadURL) {
//		this.downloadURL = downloadURL;
//	}
//
//	public String getDisplayMessage() {
//		return displayMessage;
//	}
//
//	public void setDisplayMessage(String displayMessage) {
//		this.displayMessage = displayMessage;
//	}
//
//	public String getVersionCode() {
//		return versionCode;
//	}
//
//	public void setVersionCode(String versionCode) {
//		this.versionCode = versionCode;
//	}
//
//	public String getApkName() {
//		return apkName;
//	}
//
//	public void setApkName(String apkName) {
//		this.apkName = apkName;
//	}
//
//
//	@Override
//	public String toString() {
//		return "VersionInfo{" +
//				"version='" + version + '\'' +
//				", updateTime='" + updateTime + '\'' +
//				", downloadURL='" + downloadURL + '\'' +
//				", displayMessage='" + displayMessage + '\'' +
//				", versionCode='" + versionCode + '\'' +
//				", apkName='" + apkName + '\'' +
//				'}';
//	}
}
