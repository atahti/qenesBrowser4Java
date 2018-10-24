package data;

public enum ENTRYS {
		NA, BIRTH, CHRISTENING, CONFIRMATION, DEATH, EMIGRATION, IMMIGRATION, USEREVENT, ICENSUS, WILL, GRADUATION, BURIED, CREM, ADOPTATION, RETI, PROB, NATU, BLESSING, OCCUPATION, EDUCATION, NATIONALITY, TITLE, PROPERTY, RELIGION, RESIDENCE, SOCIAL_SECURITY_NRO, CAST, DSCR, NCHI, NMR, IDNO, MARRIAGE, DIVORCE, ANNULMENT, CENSUS, DIVORCE_FILED, ENGAGEMENT, MARRIAGE_BANN, MARRIAGE_CONTRACT, MARRIAGE_LICENSE, MARRIAGE_SETTLEMENT, EVEN;
		
		public static int toInt(ENTRYS en) {
			switch (en) {
				case NA:	 				{ return 0; }
				case BIRTH: 				{ return 1; }
				case CHRISTENING: 			{ return 2; }
				case CONFIRMATION: 			{ return 3; }
				case DEATH: 				{ return 4; }
				case EMIGRATION: 			{ return 5; }
				case IMMIGRATION: 			{ return 6; }
				case USEREVENT: 			{ return 7; }
				case ICENSUS: 				{ return 8; }
				case WILL: 					{ return 9; }
				case GRADUATION: 			{ return 10; }
				case BURIED: 				{ return 11; }
				case CREM: 					{ return 12; }
				case ADOPTATION: 			{ return 13; }
				case RETI: 					{ return 14; }
				case PROB: 					{ return 15; }
				case NATU: 					{ return 16; }
				case BLESSING: 				{ return 17; } 
				case OCCUPATION: 			{ return 18; }
				case EDUCATION: 			{ return 19; }
				case NATIONALITY: 			{ return 20; }
				case TITLE: 				{ return 21; }
				case PROPERTY: 				{ return 22; }
				case RELIGION: 				{ return 23; }
				case RESIDENCE: 			{ return 24; }
				case SOCIAL_SECURITY_NRO:	{ return 25; }
				case CAST: 					{ return 26; }
				case DSCR: 					{ return 27; }
				case NCHI: 					{ return 28; }
				case NMR: 					{ return 29; }
				case IDNO: 					{ return 30; }
				case MARRIAGE: 				{ return 31; }
				case DIVORCE: 				{ return 32; }
				case ANNULMENT: 			{ return 33; }
				case CENSUS: 				{ return 34; }
				case DIVORCE_FILED: 		{ return 35; }
				case ENGAGEMENT: 			{ return 36; }
				case MARRIAGE_BANN: 		{ return 37; }
				case MARRIAGE_CONTRACT: 	{ return 38; }
				case MARRIAGE_LICENSE: 		{ return 39; }
				case MARRIAGE_SETTLEMENT: 	{ return 40; }
				case EVEN: 					{ return 41; }
				default: 					{ return -1; }
			}
		}
		public static ENTRYS fromInt(int num) {
		switch (num) {
				case 0: { return NA; }
				case 1: { return BIRTH; }
				case 2: { return CHRISTENING; }
				case 3: { return CONFIRMATION; }
				case 4: { return DEATH; } 
				case 5: { return EMIGRATION; } 
				case 6: { return IMMIGRATION; }
				case 7: { return USEREVENT; }
				case 8: { return ICENSUS; }
				case 9: { return WILL; } 
				case 10: { return GRADUATION; }
				case 11: { return BURIED; }
				case 12: { return CREM; }
				case 13: { return ADOPTATION; }
				case 14: { return RETI; }
				case 15: { return PROB; }
				case 16: { return NATU; }
				case 17: { return BLESSING; }
				case 18: { return OCCUPATION; }
				case 19: { return EDUCATION; }
				case 20: { return NATIONALITY; }
				case 21: { return TITLE; }
				case 22: { return PROPERTY; }
				case 23: { return RELIGION; }
				case 24: { return RESIDENCE; }
				case 25: { return SOCIAL_SECURITY_NRO; }
				case 26: { return CAST; }
				case 27: { return DSCR; }
				case 28: { return NCHI; }
				case 29: { return NMR; }
				case 30: { return IDNO; }
				case 31: { return MARRIAGE; }
				case 32: { return DIVORCE; }
				case 33: { return ANNULMENT; }
				case 34: { return CENSUS; } 
				case 35: { return DIVORCE_FILED; }
				case 36: { return ENGAGEMENT; }
				case 37: { return MARRIAGE_BANN; }
				case 38: { return MARRIAGE_CONTRACT; }
				case 39: { return MARRIAGE_LICENSE; }
				case 40: { return MARRIAGE_SETTLEMENT; }
				case 41: { return EVEN; }
				
				default: { return null; }
			}
		}
		
		public static String toString(ENTRYS type) {
			return toString(ENTRYS.toInt(type));
		}
		
		public static String toString(Integer type) {
			switch (type) {
				case 0: { return "NA"; }
				case 1: { return "Birth"; }
				case 2: { return "Christening"; }
				case 3: { return "Confirmation"; }
				case 4: { return "Death"; } 
				case 5: { return "Emigration"; } 
				case 6: { return "Immigration"; }
				case 7: { return "Usr event"; }
				case 8: { return "Census"; }
				case 9: { return "Will"; } 
				case 10: { return "Graduation"; }
				case 11: { return "Buried"; }
				case 12: { return "Crematorized"; }
				case 13: { return "Adoptation"; }
				case 14: { return "Retirement"; }
				case 15: { return "Prob"; }
				case 16: { return "Naturalization"; }
				case 17: { return "Blessing"; }
				case 18: { return "Occupation"; }
				case 19: { return "Education"; }
				case 20: { return "Nationality"; }
				case 21: { return "Title"; }
				case 22: { return "Property"; }
				case 23: { return "Religion"; }
				case 24: { return "Residence"; }
				case 25: { return "SSN"; }
				case 26: { return "Caste"; }
				case 27: { return "DSCR"; }
				case 28: { return "NCHI"; }
				case 29: { return "NMR"; }
				case 30: { return "IDNO"; }				
				case 31: { return "Marriage"; }
				case 32: { return "Divorce"; }
				case 33: { return "Annulment"; }
				case 34: { return "Census"; } 
				case 35: { return "Divorce Filed"; }
				case 36: { return "Engagement"; }
				case 37: { return "Marriage Bann"; }
				case 38: { return "Marriage Contract"; }
				case 39: { return "Marriage License"; }
				case 40: { return "Marriage Settlement"; }
				case 41: { return "User fam event"; }
				default: { return "ERROR"; }
			}
		}
	}
