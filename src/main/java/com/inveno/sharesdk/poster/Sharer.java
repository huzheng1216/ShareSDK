package com.inveno.sharesdk.poster;

import com.inveno.sharesdk.data.ShareData;

public interface Sharer {
	abstract void share(ShareData sd);
	abstract void shareText(ShareData sd);
	abstract void shareBmp(ShareData sd);
	abstract void shareTextBmp(ShareData sd);
	abstract void shareMusic(ShareData sd);
	abstract void shareVideo(ShareData sd);
	abstract void shareWeb(ShareData sd);
	abstract void shareApp(ShareData sd);
	abstract void shareVoice(ShareData sd);
	abstract void send();
}
