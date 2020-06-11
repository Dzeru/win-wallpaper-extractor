# -*- mode: python -*-
a = Analysis(['wallex.py'],
             hiddenimports=[],
             hookspath=None,
             runtime_hooks=None)

pyz = PYZ(a.pure)
exe = EXE(pyz,
          a.scripts,
          a.binaries,
          a.zipfiles,
          a.datas,
          name='wallex.exe',
          debug=False,
          strip=None,
          upx=True,
          console=False,
          icon='C:\\bagpack\\aprojects\\wallex\\python-version\\wallex.ico')